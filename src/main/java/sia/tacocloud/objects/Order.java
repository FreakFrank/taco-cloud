package sia.tacocloud.objects;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private long id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "street cannot be empty")
    private String street;
    @NotBlank(message = "city cannot be empty")
    private String city;
    @NotBlank(message = "state cannot be empty")
    private String state;
    @NotBlank(message = "zip cannot be empty")
    private String zip;
    @CreditCardNumber(message = "ccNumber cannot be empty")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "ccCVV cannot be empty")
    private String ccCVV;
    private Date placedAt;
    List <Taco> tacoList = new ArrayList<>();

    public void addDesign(Taco saved) {
        tacoList.add(saved);
    }

    public List<Taco> getTacos() {
        return tacoList;
    }
}
