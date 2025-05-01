package at.htlkaindorf.eventmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDetailDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<EventDTO> events;
}
