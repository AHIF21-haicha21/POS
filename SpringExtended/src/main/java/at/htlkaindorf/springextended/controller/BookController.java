package at.htlkaindorf.springextended.controller;

import at.htlkaindorf.springextended.dto.BookDTO;
import at.htlkaindorf.springextended.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Iterable<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/paged")
    private ResponseEntity<Iterable<BookDTO>> getAllBooksPage(
            Pageable pageable
    ) {
        return ResponseEntity.ok(bookService.findAllBooks(pageable));
    }
}
