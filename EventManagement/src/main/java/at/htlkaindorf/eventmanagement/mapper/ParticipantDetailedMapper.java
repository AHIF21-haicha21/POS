package at.htlkaindorf.eventmanagement.mapper;

import at.htlkaindorf.eventmanagement.dto.ParticipantDTO;
import at.htlkaindorf.eventmanagement.dto.ParticipantDetailDTO;
import at.htlkaindorf.eventmanagement.pojos.Participant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface ParticipantDetailedMapper {
    ParticipantDetailDTO toDTO(Participant participant);

    Participant toEntity(ParticipantDetailDTO dto);
}
