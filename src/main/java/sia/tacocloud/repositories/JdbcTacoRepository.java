package sia.tacocloud.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import sia.tacocloud.objects.Taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTacoRepository (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco design) {
        long id = saveTacoInfo(design);
        for (String ingredient : design.getIngredients()) {
            saveIngredientToTaco(id, ingredient);
        }
        design.setId(id);
        return design;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory("INSERT INTO Taco (name, createdAt) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP);
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory.newPreparedStatementCreator(Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private void saveIngredientToTaco(long tacoId, String ingredient) {
        jdbcTemplate.update("INSERT INTO Taco_Ingredients (taco, ingredient) values (?, ?)", tacoId, ingredient);
    }
}
