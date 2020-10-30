package sia.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.objects.Order;

public interface OrderRepository extends CrudRepository <Order, Long> {

}
