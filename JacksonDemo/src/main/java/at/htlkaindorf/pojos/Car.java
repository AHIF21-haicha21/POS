package at.htlkaindorf.pojos;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Car {
    @JsonAlias({"brand"}) // nimmt key brand
    @JsonProperty("brand") // schreibt key brand ohne das schreibt variablenname
    private String carBrand;
    @JsonAlias({"model"})
    @JsonProperty("model")
    private String carModel;
    @JsonAlias({"modelYear"})
    @JsonProperty("modelYear")
    private int year;

  //  @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL) // wird nur geschrieben wenn es nicht null ist
    private String carVin;

    private List<Holder> holders;

}
