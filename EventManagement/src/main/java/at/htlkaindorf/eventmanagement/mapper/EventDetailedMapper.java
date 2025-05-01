package at.htlkaindorf.eventmanagement.mapper;

import at.htlkaindorf.eventmanagement.dto.EventDetailDTO;
import at.htlkaindorf.eventmanagement.pojos.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LocationMapper.class, OrganizerMapper.class, ParticipantMapper.class})
public interface EventDetailedMapper {

    EventDetailDTO toDTO(Event event);

    Event toEntitiy(EventDetailDTO dto);
}
