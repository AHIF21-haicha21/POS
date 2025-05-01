package at.htlkaindorf.eventmanagement.mapper;

import at.htlkaindorf.eventmanagement.dto.EventDTO;
import at.htlkaindorf.eventmanagement.dto.EventDetailDTO;
import at.htlkaindorf.eventmanagement.pojos.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDTO toDTO(Event event);

    Event toEntity(EventDTO dto);
}
