package at.htlkaindorf.eventmanagement.mapper;

import at.htlkaindorf.eventmanagement.dto.OrganizerDTO;
import at.htlkaindorf.eventmanagement.dto.ParticipantDTO;
import at.htlkaindorf.eventmanagement.dto.ParticipantDetailDTO;
import at.htlkaindorf.eventmanagement.pojos.Organizer;
import at.htlkaindorf.eventmanagement.pojos.Participant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface ParticipantMapper {
    ParticipantDTO toDTO(Participant participant);

    ParticipantDetailDTO toDetailedDTO(Participant participant);

    Participant toEntity(ParticipantDTO dto);

    Participant toEntity(ParticipantDetailDTO dto);
}
