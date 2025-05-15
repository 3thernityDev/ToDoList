package re.ethernity.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import re.ethernity.todolist.Entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}