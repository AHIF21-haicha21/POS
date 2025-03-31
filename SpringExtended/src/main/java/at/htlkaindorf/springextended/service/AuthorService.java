package at.htlkaindorf.springextended.service;

import at.htlkaindorf.springextended.dto.AuthorDTO;
import at.htlkaindorf.springextended.mapper.AuthorMapper;
import at.htlkaindorf.springextended.pojos.Author;
import at.htlkaindorf.springextended.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    public final AuthorMapper authorMapper;
    public final AuthorRepository authorRepository;

    public List<AuthorDTO> findAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream()
                .map(authorMapper::toDTO)
                .toList();

    }
}
