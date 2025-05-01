package at.htlkaindorf.eventmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private String id;
    private String title;
    @JsonFormat(pattern = "ddMMyyyy")
    private LocalDate date;
    private Integer maxParticipant;
}
