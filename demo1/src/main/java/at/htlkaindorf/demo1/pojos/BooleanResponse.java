package at.htlkaindorf.demo1.pojos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooleanResponse {
    @JsonAlias("true")
    @JsonProperty("true")
    private boolean value;
}
