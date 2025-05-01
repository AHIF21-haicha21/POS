package at.htlkaindorf.eventmanagement.mapper;

import at.htlkaindorf.eventmanagement.dto.EventDTO;
import at.htlkaindorf.eventmanagement.dto.LocationDTO;
import at.htlkaindorf.eventmanagement.dto.LocationDetailDTO;
import at.htlkaindorf.eventmanagement.pojos.Event;
import at.htlkaindorf.eventmanagement.pojos.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toDTO(Location location);

    Location toEntity(LocationDTO dto);
}
