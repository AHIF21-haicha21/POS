package at.htlkaindorf.eventmanagement.controller;

import at.htlkaindorf.eventmanagement.dto.OrganizerDTO;
import at.htlkaindorf.eventmanagement.dto.OrganizerDetailDTO;
import at.htlkaindorf.eventmanagement.dto.OrganizerDTO;
import at.htlkaindorf.eventmanagement.dto.OrganizerDetailDTO;
import at.htlkaindorf.eventmanagement.service.OrganizerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/organizer")
@RequiredArgsConstructor
public class OrganizerController {
    private final OrganizerService organizerService;

    @GetMapping("/all")
    public ResponseEntity<List<OrganizerDTO>> getAllOrganizers() {
        List<OrganizerDTO> dtos = organizerService.getAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{organizerId}")
    public ResponseEntity<OrganizerDetailDTO> getOrganizerById(@PathVariable Long organizerId) {
        Optional<OrganizerDetailDTO> dto = organizerService.getOrganizer(organizerId);

        return ResponseEntity.of(dto);
    }

    @PostMapping("/new")
    public ResponseEntity<OrganizerDetailDTO> addOrganizer(@RequestBody OrganizerDetailDTO organizerDto) {
        OrganizerDetailDTO newOrganizerDto = organizerService.add(organizerDto);

        return ResponseEntity.ok(newOrganizerDto);
    }

    @PutMapping("/update/{organizerId}")
    public ResponseEntity<OrganizerDetailDTO> updateOrganizer(@PathVariable Long organizerId, @RequestBody OrganizerDetailDTO organizerDto) {
        Optional<OrganizerDetailDTO> updatedOrganizer = organizerService.update(organizerId, organizerDto);

        return ResponseEntity.of(updatedOrganizer);
    }

    @DeleteMapping("/{organizerId}")
    public ResponseEntity<OrganizerDetailDTO> deleteOrganizer(@PathVariable Long organizerId) {
        Optional<OrganizerDetailDTO> deletedOrganizer = organizerService.delete(organizerId);

        return ResponseEntity.of(deletedOrganizer);
    }
}
