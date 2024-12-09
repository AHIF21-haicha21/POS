package at.htlkaindorf.todo_list.pojos;

import at.htlkaindorf.todo_list.annotations.GeneratedValue;
import at.htlkaindorf.todo_list.annotations.ImmutableProperty;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @JsonAlias("catId")
    @GeneratedValue(step = 20, startValue = 100)
    @ImmutableProperty
    private Long id;
    @JsonAlias("name")
    private String categoryName;
    @JsonAlias("description")
    private String categoryDescription;
}
