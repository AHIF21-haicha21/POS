package at.htlkaindorf.booksdb.database;

import at.htlkaindorf.booksdb.pojos.Author;
import at.htlkaindorf.booksdb.pojos.Book;
import at.htlkaindorf.booksdb.pojos.Publisher;
import at.htlkaindorf.booksdb.repositories.AuthorRepository;
import at.htlkaindorf.booksdb.repositories.BookRepository;
import at.htlkaindorf.booksdb.repositories.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitDatabase {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @PostConstruct
    private void initDB() {
        final String dataSourceFilename = "booksdb.json";

        ObjectMapper mapper = new ObjectMapper();

        try {
            List<Publisher> publishers = mapper.readerForListOf(Publisher.class).readValue(this.getClass().getResourceAsStream("/" + dataSourceFilename));

            List<Author> authors = new ArrayList<>();
            publishers.forEach(p -> {
                p.getBooks().forEach(b -> {
                    b.getAuthors().forEach(a -> {
                        List<Author> a3 = authors.stream().filter(a2 -> a2.getFirstname().equals(a.getFirstname()) && a2.getLastname().equals(a.getLastname())).toList();
                        if (!a3.isEmpty()) {
                            a3.get(0).getBooks().add(b);
                            b.getAuthors().set(b.getAuthors().indexOf(a), a3.get(0));
                        } else {
                            a.getBooks().add(b);
                            authors.add(a);
                        }
                    });
                });
            });

            publisherRepository.saveAll(publishers);
        } catch (IOException e) {
            log.error(String.format("Error while reading %s", dataSourceFilename));
            throw new RuntimeException(e);
        }

        log.info(String.format("Publishers imported: %d", publisherRepository.count()));
        log.info(String.format("Books imported: %d", bookRepository.count()));
        log.info(String.format("Authors imported: %d", authorRepository.count()));

        runQueries();
    }

    private void runQueries() {

        List<Author> authors = publisherRepository.getAllAuthorsOfPublisher("Harper");
        authors.forEach(a -> log.info(a.toString()));

        List<Author> authors2 = publisherRepository.getAuthorsWithAtLeastNumberBooks(10);
        authors2.forEach(a -> log.info(a.toString()));

        List<Book> books = bookRepository.getBooksWithPriceOverMaxPriceOfPublisher("Harper");
        books.forEach(a -> log.info(a.toString()));
    }
}
