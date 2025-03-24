package at.htlkaindorf.springextended.mapper;

import at.htlkaindorf.springextended.dto.PublisherDTO;
import at.htlkaindorf.springextended.pojos.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapper_Legacy {
    public PublisherDTO toDTO(Publisher publisher) {
        if (publisher == null) {
            return null;
        }

        PublisherDTO dto = new PublisherDTO();
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());

        return dto;
    }

    public Publisher toEntity(PublisherDTO dto) {
        if (dto == null) {
            return null;
        }

        Publisher publisher = new Publisher();
        publisher.setId(dto.getId());
        publisher.setName(dto.getName());

        return publisher;
    }
}
