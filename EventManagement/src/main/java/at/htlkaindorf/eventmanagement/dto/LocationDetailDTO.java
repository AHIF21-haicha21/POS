package at.htlkaindorf.eventmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDetailDTO {
    private Integer id;
    private String name;
    private String address;
    private Integer capacity;
    private List<EventDTO> events;
}
