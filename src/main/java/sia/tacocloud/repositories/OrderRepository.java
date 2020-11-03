package sia.tacocloud.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.objects.Order;
import sia.tacocloud.objects.User;

import java.util.List;

public interface OrderRepository extends CrudRepository <Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
