package at.htlkaindorf.springextended.controller;

import at.htlkaindorf.springextended.dto.AuthorDTO;
import at.htlkaindorf.springextended.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<Iterable<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.findAllAuthors());
    }
}
