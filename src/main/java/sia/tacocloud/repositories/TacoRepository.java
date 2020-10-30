package sia.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.objects.Taco;

public interface TacoRepository extends CrudRepository <Taco, Long> {

}
