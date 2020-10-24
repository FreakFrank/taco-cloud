package sia.tacocloud.objects;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import sia.tacocloud.enums.Type;

@Data
@RequiredArgsConstructor
public class Ingredient {
    public final String id;
    public final String name;
    public final Type type;
}
