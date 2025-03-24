package at.htlkaindorf.springextended.mapper;

import at.htlkaindorf.springextended.dto.PublisherDTO;
import at.htlkaindorf.springextended.pojos.Publisher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherDTO toDTO(Publisher publisher);

    Publisher toEntity(PublisherDTO publisherDTO);
}
