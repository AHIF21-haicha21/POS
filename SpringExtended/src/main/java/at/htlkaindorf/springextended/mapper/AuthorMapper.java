package at.htlkaindorf.springextended.mapper;

import at.htlkaindorf.springextended.dto.AuthorDTO;
import at.htlkaindorf.springextended.pojos.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mappings({
            @Mapping(source = "id", target = "authorId"),
            @Mapping(source = "name", target = "authorName")
    })
    AuthorDTO toDTO(Author author);
}
