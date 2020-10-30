package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sia.tacocloud.enums.Type;
import sia.tacocloud.enums.View;
import sia.tacocloud.objects.Ingredient;
import sia.tacocloud.objects.Order;
import sia.tacocloud.objects.Taco;
import sia.tacocloud.repositories.IngredientRepository;
import sia.tacocloud.repositories.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    List<Ingredient> ingredients = new ArrayList<>();

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {

        fillIngredients();

        for (Type type : Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), ingredients.stream().filter(ingredient -> ingredient.type.equals(type)).collect(Collectors.toList()));
        }

        model.addAttribute("design", new Taco());
        return View.DESIGN.getValue();
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            log.info(errors.getAllErrors().toString());

            for (Type type : Type.values()) {
                model.addAttribute(type.toString().toLowerCase(), ingredients.stream().filter(ingredient -> ingredient.type.equals(type)).collect(Collectors.toList()));
            }
            return View.DESIGN.getValue();
        }
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
        log.info("Processing Design" + design);
        return View.REDIRECT.getValue() + View.ORDER.getValue() + "/" + View.CURRENT.getValue();

    }

    private void fillIngredients() {
        if (ingredients.isEmpty())
            ingredientRepository.findAll().forEach(ingredient -> ingredients.add(ingredient));
    }

}
