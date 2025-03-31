package at.htlkaindorf.springextended.mapper;

import at.htlkaindorf.springextended.dto.BookDTO;
import at.htlkaindorf.springextended.pojos.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, PublisherMapper.class})
public interface BookMapper {
    BookDTO toDTO(Book book);
}
