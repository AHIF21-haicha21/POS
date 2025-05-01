package at.htlkaindorf.eventmanagement.service;

import at.htlkaindorf.eventmanagement.dto.LocationDTO;
import at.htlkaindorf.eventmanagement.dto.LocationDetailDTO;
import at.htlkaindorf.eventmanagement.exception.StillInUseException;
import at.htlkaindorf.eventmanagement.mapper.LocationDetailedMapper;
import at.htlkaindorf.eventmanagement.mapper.LocationMapper;
import at.htlkaindorf.eventmanagement.pojos.Event;
import at.htlkaindorf.eventmanagement.pojos.Location;
import at.htlkaindorf.eventmanagement.pojos.Organizer;
import at.htlkaindorf.eventmanagement.pojos.Participant;
import at.htlkaindorf.eventmanagement.repository.EventRepository;
import at.htlkaindorf.eventmanagement.repository.LocationRepository;
import at.htlkaindorf.eventmanagement.utils.ObjectMerger;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final LocationDetailedMapper locationDetailedMapper;
    private final EventRepository eventRepository;

    public List<LocationDTO> getAll() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(locationMapper::toDTO).toList();
    }

    public Optional<LocationDetailDTO> getLocation(Integer locationId) {
        Optional<Location> location = locationRepository.findById(locationId);

        return location.map(locationDetailedMapper::toDTO);
    }

    public LocationDetailDTO add(@Valid LocationDetailDTO dto) {
        Location location = locationDetailedMapper.toEntity(dto);
        List<Event> es = eventRepository.findByIdIn(location.getEvents().stream().map(Event::getId).toList());

        location.setId(null);
        location.setEvents(es);

        Location newLocation = locationRepository.save(location);
        return locationDetailedMapper.toDTO(newLocation);
    }

    public Optional<LocationDetailDTO> update(Integer locationId, @Valid LocationDetailDTO dto) {
        Optional<Location> optLocation = locationRepository.findById(locationId);
        if (optLocation.isEmpty())
            return Optional.empty();

        Location location = optLocation.get();
        Location newLocationValues = locationDetailedMapper.toEntity(dto);

        List<Event> es = newLocationValues
                .getEvents()
                .stream()
                .map(m -> {
                    Optional<Event> e = eventRepository.findById(m.getId());
                    return e.map(event -> ObjectMerger.merge(event, m)).orElse(m);
                })
                .toList();

        location.setEvents(es);

        Location updatedLocation = ObjectMerger.merge(location, newLocationValues);
        return Optional.of(locationDetailedMapper.toDTO(locationRepository.save(updatedLocation)));
    }

    @Transactional
    public Optional<LocationDetailDTO> delete(Integer locationId) {
        Optional<Location> optLocation = locationRepository.findById(locationId);
        if (optLocation.isEmpty())
            return Optional.empty();
        Location location = optLocation.get();

        if(!location.getEvents().isEmpty())
            throw new StillInUseException(String.format("The location '%s' is still in use", location.getName()));

        LocationDetailDTO dto = locationDetailedMapper.toDTO(location);
        locationRepository.deleteById(location.getId());
        return Optional.of(dto);
    }
}
