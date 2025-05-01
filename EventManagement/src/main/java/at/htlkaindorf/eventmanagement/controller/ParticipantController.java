package at.htlkaindorf.eventmanagement.controller;

import at.htlkaindorf.eventmanagement.dto.ParticipantDTO;
import at.htlkaindorf.eventmanagement.dto.ParticipantDetailDTO;
import at.htlkaindorf.eventmanagement.dto.ParticipantDTO;
import at.htlkaindorf.eventmanagement.dto.ParticipantDetailDTO;
import at.htlkaindorf.eventmanagement.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/participant")
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;

    @GetMapping("/all")
    public ResponseEntity<List<ParticipantDTO>> getAllParticipants() {
        List<ParticipantDTO> dtos = participantService.getAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{participantId}")
    public ResponseEntity<ParticipantDetailDTO> getParticipantById(@PathVariable Long participantId) {
        Optional<ParticipantDetailDTO> dto = participantService.getParticipant(participantId);

        return ResponseEntity.of(dto);
    }

    @PostMapping("/new")
    public ResponseEntity<ParticipantDetailDTO> addParticipant(@RequestBody ParticipantDetailDTO participantDto) {
        ParticipantDetailDTO newParticipantDto = participantService.add(participantDto);

        return ResponseEntity.ok(newParticipantDto);
    }

    @PutMapping("/update/{participantId}")
    public ResponseEntity<ParticipantDetailDTO> updateParticipant(@PathVariable Long participantId, @RequestBody ParticipantDetailDTO participantDto) {
        Optional<ParticipantDetailDTO> updatedParticipant = participantService.update(participantId, participantDto);

        return ResponseEntity.of(updatedParticipant);
    }

    @DeleteMapping("/{participantId}")
    public ResponseEntity<ParticipantDetailDTO> deleteParticipant(@PathVariable Long participantId) {
        Optional<ParticipantDetailDTO> deletedParticipant = participantService.delete(participantId);

        return ResponseEntity.of(deletedParticipant);
    }
}