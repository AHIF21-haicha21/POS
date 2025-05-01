package at.htlkaindorf.eventmanagement.service;

import at.htlkaindorf.eventmanagement.dto.EventDTO;
import at.htlkaindorf.eventmanagement.dto.EventDetailDTO;
import at.htlkaindorf.eventmanagement.dto.ParticipantDetailDTO;
import at.htlkaindorf.eventmanagement.exception.ParticipationException;
import at.htlkaindorf.eventmanagement.mapper.EventDetailedMapper;
import at.htlkaindorf.eventmanagement.mapper.EventMapper;
import at.htlkaindorf.eventmanagement.mapper.ParticipantDetailedMapper;
import at.htlkaindorf.eventmanagement.pojos.Event;
import at.htlkaindorf.eventmanagement.pojos.Location;
import at.htlkaindorf.eventmanagement.pojos.Organizer;
import at.htlkaindorf.eventmanagement.pojos.Participant;
import at.htlkaindorf.eventmanagement.repository.EventRepository;
import at.htlkaindorf.eventmanagement.repository.LocationRepository;
import at.htlkaindorf.eventmanagement.repository.OrganizerRepository;
import at.htlkaindorf.eventmanagement.repository.ParticipantRepository;
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
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final EventDetailedMapper eventDetailedMapper;

    private final ParticipantRepository participantRepository;
    private final ParticipantDetailedMapper participantDetailedMapper;

    private final LocationRepository locationRepository;
    private final OrganizerRepository organizerRepository;

    public List<EventDTO> getAll() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(eventMapper::toDTO).toList();
    }

    public Optional<EventDetailDTO> getEvent(String eventId) {
        Optional<Event> event = eventRepository.findById(eventId);

        return event.map(eventDetailedMapper::toDTO);
    }

    public EventDetailDTO add(@Valid EventDetailDTO dto) {
        Event event = eventDetailedMapper.toEntitiy(dto);
        Optional<Location> l = locationRepository.findById(event.getLocation().getId());
        Optional<Organizer> o = organizerRepository.findById(event.getOrganizer().getId());
        List<Participant> ps = event
                .getParticipants()
                .stream()
                .map(p -> participantRepository.findById(p.getId()).orElse(p))
                .toList();

        event.setId(null);
        l.ifPresent(event::setLocation);
        event.setParticipants(ps);
        o.ifPresent(event::setOrganizer);

        Event newEvent = eventRepository.save(event);
        return eventDetailedMapper.toDTO(newEvent);
    }

    public Optional<EventDetailDTO> update(String eventid, @Valid EventDetailDTO dto) {
        Optional<Event> optEvent = eventRepository.findById(eventid);
        if (optEvent.isEmpty())
            return Optional.empty();

        Optional<Organizer> optOrg = organizerRepository.findById(dto.getOrganizer().getId());
        if (optOrg.isEmpty())
            return Optional.empty();

        Optional<Location> optLoc = locationRepository.findById(dto.getLocation().getId());
        if (optLoc.isEmpty())
            return Optional.empty();

        Event oldEvent = optEvent.get();
        oldEvent.setOrganizer(optOrg.get());
        oldEvent.setLocation(optLoc.get());

        Event newEventValues = eventDetailedMapper.toEntitiy(dto);
        Event updatedEvent = ObjectMerger.merge(oldEvent, newEventValues);
        return Optional.of(eventDetailedMapper.toDTO(eventRepository.save(updatedEvent)));
    }

    @Transactional
    public Optional<EventDetailDTO> delete(String eventId) {
        Optional<Event> optEvent = eventRepository.findById(eventId);
        if (optEvent.isEmpty())
            return Optional.empty();
        Event event = optEvent.get();
        EventDetailDTO dto = eventDetailedMapper.toDTO(event);

        event.getParticipants().forEach(p -> p.getEvents().remove(event));
        event.getParticipants().clear();
        event.getLocation().getEvents().remove(event);
        eventRepository.save(event);
        eventRepository.deleteById(event.getId());
        return Optional.of(dto);
    }

    public Optional<ParticipantDetailDTO> enrollParticipant(String eventId, Long participantId) throws ParticipationException {
        Optional<Event> optEvent = eventRepository.findById(eventId);
        if (optEvent.isEmpty())
            return Optional.empty();
        Event event = optEvent.get();

        Optional<Participant> optPar = participantRepository.findById(participantId);
        if (optPar.isEmpty())
            return Optional.empty();
        Participant participant = optPar.get();

        if (eventRepository.participatesInEvent(event, participant))
            throw new ParticipationException("Participant already participates");

        event.getParticipants().add(participant);
        participant.getEvents().add(event);

        return Optional.of(participantDetailedMapper.toDTO(participantRepository.save(participant)));
    }

    public Optional<ParticipantDetailDTO> dropParticipant(String eventId, Long participantId) throws ParticipationException {
        Optional<Event> optEvent = eventRepository.findById(eventId);
        if (optEvent.isEmpty())
            return Optional.empty();
        Event event = optEvent.get();

        Optional<Participant> optPar = participantRepository.findById(participantId);
        if (optPar.isEmpty())
            return Optional.empty();
        Participant participant = optPar.get();

        if (!eventRepository.participatesInEvent(event, participant))
            throw new ParticipationException("Participant does not participate");

        event.getParticipants().remove(participant);
        participant.getEvents().remove(event);

        return Optional.of(participantDetailedMapper.toDTO(participantRepository.save(participant)));
    }

}
