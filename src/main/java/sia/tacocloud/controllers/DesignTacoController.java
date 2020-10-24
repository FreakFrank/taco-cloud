package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.enums.Type;
import sia.tacocloud.enums.View;
import sia.tacocloud.objects.Ingredient;
import sia.tacocloud.objects.Taco;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    List<Ingredient> ingredients = new ArrayList<>();

    @GetMapping
    public String showDesignForm(Model model) {

        fillIngredients();

        for (Type type : Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), ingredients.stream().filter(ingredient -> ingredient.type.equals(type)).collect(Collectors.toList()));
        }

        model.addAttribute("design", new Taco());
        return View.DESIGN.getValue();
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            log.info(errors.getAllErrors().toString());

            for (Type type : Type.values()) {
                model.addAttribute(type.toString().toLowerCase(), ingredients.stream().filter(ingredient -> ingredient.type.equals(type)).collect(Collectors.toList()));
            }
            return View.DESIGN.getValue();
        }

        log.info("Processing Design" + design);
        return View.REDIRECT.getValue() + View.ORDER.getValue() + "/" + View.CURRENT.getValue();

    }

    private void fillIngredients() {
        ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
    }

}
