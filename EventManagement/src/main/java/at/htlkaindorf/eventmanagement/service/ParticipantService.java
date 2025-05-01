package at.htlkaindorf.eventmanagement.service;

import at.htlkaindorf.eventmanagement.dto.ParticipantDTO;
import at.htlkaindorf.eventmanagement.dto.ParticipantDetailDTO;
import at.htlkaindorf.eventmanagement.exception.StillInUseException;
import at.htlkaindorf.eventmanagement.mapper.ParticipantDetailedMapper;
import at.htlkaindorf.eventmanagement.mapper.ParticipantMapper;
import at.htlkaindorf.eventmanagement.pojos.Event;
import at.htlkaindorf.eventmanagement.pojos.Participant;
import at.htlkaindorf.eventmanagement.repository.EventRepository;
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
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private  final ParticipantMapper participantMapper;
    private final ParticipantDetailedMapper participantDetailedMapper;

    private final EventRepository eventRepository;

    public List<ParticipantDTO> getAll() {
        List<Participant> participants = participantRepository.findAll();
        return participants.stream().map(participantMapper::toDTO).toList();
    }

    public Optional<ParticipantDetailDTO> getParticipant(Long participantId) {
        Optional<Participant> participant = participantRepository.findById(participantId);

        return participant.map(participantDetailedMapper::toDTO);
    }

    public ParticipantDetailDTO add(@Valid ParticipantDetailDTO dto) {
        Participant participant = participantDetailedMapper.toEntity(dto);
        List<Event> es = eventRepository.findByIdIn(participant.getEvents().stream().map(Event::getId).toList());

        participant.setId(null);
        participant.setEvents(es);

        Participant newParticipant = participantRepository.save(participant);
        return participantDetailedMapper.toDTO(newParticipant);
    }

    public Optional<ParticipantDetailDTO> update(Long participantId, @Valid ParticipantDetailDTO dto) {
        Optional<Participant> optParticipant = participantRepository.findById(participantId);
        if (optParticipant.isEmpty())
            return Optional.empty();

        Participant participant = optParticipant.get();
        Participant newParticipantValues = participantDetailedMapper.toEntity(dto);

        List<Event> es = newParticipantValues
                .getEvents()
                .stream()
                .map(m -> {
                    Optional<Event> e = eventRepository.findById(m.getId());
                    return e.map(event -> ObjectMerger.merge(event, m)).orElse(m);
                })
                .toList();

        participant.setEvents(es);

        Participant updatedParticipant = ObjectMerger.merge(participant, newParticipantValues);
        return Optional.of(participantDetailedMapper.toDTO(participantRepository.save(updatedParticipant)));
    }

    @Transactional
    public Optional<ParticipantDetailDTO> delete(Long participantId) {
        Optional<Participant> optParticipant = participantRepository.findById(participantId);
        if (optParticipant.isEmpty())
            return Optional.empty();
        Participant participant = optParticipant.get();

        participant.getEvents().forEach(e -> e.getParticipants().remove(participant));

        ParticipantDetailDTO dto = participantDetailedMapper.toDTO(participant);
        participantRepository.save(participant);
        participantRepository.deleteById(participant.getId());
        return Optional.of(dto);
    }
}
