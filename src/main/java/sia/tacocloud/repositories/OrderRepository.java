package sia.tacocloud.repositories;

import sia.tacocloud.objects.Order;

public interface OrderRepository {

    Order save(Order order);

}
