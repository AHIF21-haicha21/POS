package at.htlkaindorf.springextended.service;

import at.htlkaindorf.springextended.dto.BookDTO;
import at.htlkaindorf.springextended.mapper.BookMapper;
import at.htlkaindorf.springextended.pojos.Book;
import at.htlkaindorf.springextended.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public List<BookDTO> findAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }
}
