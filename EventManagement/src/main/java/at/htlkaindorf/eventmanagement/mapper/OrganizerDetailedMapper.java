package at.htlkaindorf.eventmanagement.mapper;

import at.htlkaindorf.eventmanagement.dto.OrganizerDTO;
import at.htlkaindorf.eventmanagement.dto.OrganizerDetailDTO;
import at.htlkaindorf.eventmanagement.pojos.Organizer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizerDetailedMapper {
    OrganizerDetailDTO toDTO(Organizer organizer);

    Organizer toEntity(OrganizerDetailDTO dto);
}
