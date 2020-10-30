package sia.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.objects.Ingredient;

public interface IngredientRepository extends CrudRepository <Ingredient, String> {
    @Override
    Iterable<Ingredient> findAll();
}
