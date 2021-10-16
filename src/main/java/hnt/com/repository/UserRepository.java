package hnt.com.repository;

import hnt.com.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByNameAndPassword(String name, String password);

    User findByName(String name);
}
