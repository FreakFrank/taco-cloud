package sia.tacocloud.objects;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createdAt;

    @NotBlank
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "You must at least choose 1 ingredient")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt(){
        createdAt = new Date();
    }
}
