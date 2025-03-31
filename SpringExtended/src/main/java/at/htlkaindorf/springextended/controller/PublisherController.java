package at.htlkaindorf.springextended.controller;

import at.htlkaindorf.springextended.dto.PublisherDTO;
import at.htlkaindorf.springextended.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    private ResponseEntity<PublisherDTO> createPublisher(
           @Valid @RequestBody PublisherDTO publisherDTO
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.createPublisher(publisherDTO));
    }
}
