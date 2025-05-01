package at.htlkaindorf.eventmanagement.service;

import at.htlkaindorf.eventmanagement.dto.LocationDetailDTO;
import at.htlkaindorf.eventmanagement.dto.OrganizerDTO;
import at.htlkaindorf.eventmanagement.dto.OrganizerDetailDTO;
import at.htlkaindorf.eventmanagement.exception.StillInUseException;
import at.htlkaindorf.eventmanagement.mapper.OrganizerDetailedMapper;
import at.htlkaindorf.eventmanagement.mapper.OrganizerMapper;
import at.htlkaindorf.eventmanagement.pojos.Event;
import at.htlkaindorf.eventmanagement.pojos.Organizer;
import at.htlkaindorf.eventmanagement.repository.OrganizerRepository;
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
public class OrganizerService {
    private final OrganizerRepository organizerRepository;
    private final OrganizerMapper organizerMapper;
    private final OrganizerDetailedMapper organizerDetailedMapper;

    public List<OrganizerDTO> getAll() {
        List<Organizer> organizers = organizerRepository.findAll();
        return organizers.stream().map(organizerMapper::toDTO).toList();
    }

    public Optional<OrganizerDetailDTO> getOrganizer(Long organizerId) {
        Optional<Organizer> organizer = organizerRepository.findById(organizerId);

        return organizer.map(organizerDetailedMapper::toDTO);
    }

    public OrganizerDetailDTO add(@Valid OrganizerDetailDTO dto) {
        Organizer organizer = organizerDetailedMapper.toEntity(dto);

        organizer.setId(null);
        Organizer newOrganizer = organizerRepository.save(organizer);
        return organizerDetailedMapper.toDTO(newOrganizer);
    }

    public Optional<OrganizerDetailDTO> update(Long organizerId, @Valid OrganizerDetailDTO dto) {
        Optional<Organizer> optOrganizer = organizerRepository.findById(organizerId);
        if (optOrganizer.isEmpty())
            return Optional.empty();

        Organizer organizer = optOrganizer.get();
        Organizer newOrganizerValues = organizerDetailedMapper.toEntity(dto);

        Organizer updatedOrganizer = ObjectMerger.merge(organizer, newOrganizerValues);
        return Optional.of(organizerDetailedMapper.toDTO(organizerRepository.save(updatedOrganizer)));
    }

    @Transactional
    public Optional<OrganizerDetailDTO> delete(Long organizerId) {
        Optional<Organizer> optOrganizer = organizerRepository.findById(organizerId);
        if (optOrganizer.isEmpty())
            return Optional.empty();
        Organizer organizer = optOrganizer.get();

        OrganizerDetailDTO dto = organizerDetailedMapper.toDTO(organizer);
        organizerRepository.deleteById(organizer.getId());
        return Optional.of(dto);
    }
}
