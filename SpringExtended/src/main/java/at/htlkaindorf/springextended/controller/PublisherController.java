package at.htlkaindorf.springextended.controller;

import at.htlkaindorf.springextended.dto.PublisherDTO;
import at.htlkaindorf.springextended.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/publisher")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<Iterable<PublisherDTO>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublisher());
    }

    @GetMapping("{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.getPublisherById(id));
    }
}
