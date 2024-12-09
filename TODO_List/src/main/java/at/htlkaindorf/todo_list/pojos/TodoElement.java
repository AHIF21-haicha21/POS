package at.htlkaindorf.todo_list.pojos;

import at.htlkaindorf.todo_list.annotations.GeneratedValue;
import at.htlkaindorf.todo_list.annotations.ImmutableProperty;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoElement {
    public static final String DATE_FORMAT = "dd.MM.yyyy";

    @ImmutableProperty
    @GeneratedValue(startValue = 1000)
    private Long id;
    private String title;
    private String description;
    private Long catId;
    @JsonAlias("date")
    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate createdDate;
    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate dueDate;
    private Boolean isDone;

    private Importance importance = Importance.UNIMPORTANT;
    private Category category;
}
