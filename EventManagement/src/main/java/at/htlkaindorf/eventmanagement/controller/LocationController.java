package at.htlkaindorf.eventmanagement.controller;

import at.htlkaindorf.eventmanagement.dto.EventDTO;
import at.htlkaindorf.eventmanagement.dto.EventDetailDTO;
import at.htlkaindorf.eventmanagement.dto.LocationDTO;
import at.htlkaindorf.eventmanagement.dto.LocationDetailDTO;
import at.htlkaindorf.eventmanagement.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/all")
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<LocationDTO> dtos = locationService.getAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDetailDTO> getLocationById(@PathVariable Integer locationId) {
        Optional<LocationDetailDTO> dto = locationService.getLocation(locationId);

        return ResponseEntity.of(dto);
    }

    @PostMapping("/new")
    public ResponseEntity<LocationDetailDTO> addLocation(@RequestBody LocationDetailDTO locationDto) {
        LocationDetailDTO newLocationDto = locationService.add(locationDto);

        return ResponseEntity.ok(newLocationDto);
    }

    @PutMapping("/update/{locationId}")
    public ResponseEntity<LocationDetailDTO> updateLocation(@PathVariable Integer locationId, @RequestBody LocationDetailDTO locationDto) {
        Optional<LocationDetailDTO> updatedLocation = locationService.update(locationId, locationDto);

        return ResponseEntity.of(updatedLocation);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<LocationDetailDTO> deleteLocation(@PathVariable Integer locationId) {
        Optional<LocationDetailDTO> deletedLocation = locationService.delete(locationId);

        return ResponseEntity.of(deletedLocation);
    }
}
