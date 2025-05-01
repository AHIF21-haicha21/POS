package at.htlkaindorf.eventmanagement.mapper;

import at.htlkaindorf.eventmanagement.dto.LocationDTO;
import at.htlkaindorf.eventmanagement.dto.LocationDetailDTO;
import at.htlkaindorf.eventmanagement.pojos.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface LocationDetailedMapper {
    LocationDetailDTO toDTO(Location location);

    Location toEntity(LocationDetailDTO dto);
}

