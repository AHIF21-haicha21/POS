package at.htlkaindorf.springextended.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {
    private Long id;
    @Size(min = 5)
    private String name;
}
