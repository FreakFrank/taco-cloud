package sia.tacocloud.objects;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class Taco {

    @NotBlank
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @Size(min = 1, message = "You must at least choose 1 ingredient")
    private String [] ingredients;


}
