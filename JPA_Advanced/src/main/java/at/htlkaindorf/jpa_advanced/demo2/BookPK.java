package at.htlkaindorf.jpa_advanced.demo2;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BookPK implements Serializable {

    private Long authorId;
    private Long bookId;

}
