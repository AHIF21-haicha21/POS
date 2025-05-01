package at.htlkaindorf.eventmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerDTO {
    private Long id;
    private String name;
    private String email;
    private Long phone;
}
