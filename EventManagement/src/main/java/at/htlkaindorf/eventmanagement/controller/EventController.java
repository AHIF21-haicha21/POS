package at.htlkaindorf.eventmanagement.controller;

import at.htlkaindorf.eventmanagement.dto.EventDTO;
import at.htlkaindorf.eventmanagement.dto.EventDetailDTO;
import at.htlkaindorf.eventmanagement.dto.ParticipantDetailDTO;
import at.htlkaindorf.eventmanagement.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> dtos = eventService.getAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDetailDTO> getEventById(@PathVariable String eventId) {
        Optional<EventDetailDTO> dto = eventService.getEvent(eventId);

        return ResponseEntity.of(dto);
    }

    @PostMapping("/new")
    public ResponseEntity<EventDetailDTO> addEvent(@RequestBody EventDetailDTO eventDto) {
        EventDetailDTO newEventDto = eventService.add(eventDto);

        return ResponseEntity.ok(newEventDto);
    }

    @PutMapping("/update/{eventId}")
    public ResponseEntity<EventDetailDTO> updateEvent(@PathVariable String eventId, @RequestBody EventDetailDTO eventDto) {
        Optional<EventDetailDTO> updatedEvent = eventService.update(eventId, eventDto);

        return ResponseEntity.of(updatedEvent);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<EventDetailDTO> deleteEvent(@PathVariable String eventId) {
        Optional<EventDetailDTO> deletedEvent = eventService.delete(eventId);

        return ResponseEntity.of(deletedEvent);
    }

    @PostMapping("/{eventId}/enroll/{participantId}")
    public ResponseEntity<ParticipantDetailDTO> enrollParticipant(@PathVariable String eventId, @PathVariable Long participantId) {
        Optional<ParticipantDetailDTO> participant = eventService.enrollParticipant(eventId, participantId);

        return ResponseEntity.of(participant);
    }

    @PostMapping("/{eventId}/drop/{participantId}")
    public ResponseEntity<ParticipantDetailDTO> dropParticipant(@PathVariable String eventId,@PathVariable Long participantId) {
        Optional<ParticipantDetailDTO> participant = eventService.dropParticipant(eventId, participantId);

        return ResponseEntity.of(participant);
    }
}
