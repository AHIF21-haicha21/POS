package at.htlkaindorf.jacksonIntro;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Furniture {

    private String productName;

    @JsonFormat
    private float height;
    private float width;
    private float depth;
    private Difficulty difficulty;
}
