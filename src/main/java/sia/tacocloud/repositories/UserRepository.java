package sia.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.objects.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
