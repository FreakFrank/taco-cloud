package sia.tacocloud.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sia.tacocloud.enums.Type;
import sia.tacocloud.objects.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("SELECT id, name, type FROM Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbc.queryForObject("SELECT id, name, type FROM Ingredient WHERE id=?", this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("INSERT INTO Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        return new Ingredient(resultSet.getString("id"), resultSet.getString("name"), Type.valueOf(resultSet.getString("type")));
    }
}
