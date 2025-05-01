package at.htlkaindorf.eventmanagement.mapper;

import at.htlkaindorf.eventmanagement.dto.OrganizerDTO;
import at.htlkaindorf.eventmanagement.dto.OrganizerDetailDTO;
import at.htlkaindorf.eventmanagement.pojos.Organizer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizerMapper {
    OrganizerDTO toDTO(Organizer organizer);

    OrganizerDetailDTO toDetailedDTO(Organizer organizer);

    Organizer toEntity(OrganizerDTO dto);

    Organizer toEntity(OrganizerDetailDTO dto);
}
