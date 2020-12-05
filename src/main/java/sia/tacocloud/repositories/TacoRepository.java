package sia.tacocloud.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.objects.Taco;

public interface TacoRepository extends CrudRepository <Taco, Long> {
    Iterable<Taco> findAll(Pageable pageRequest);
}
