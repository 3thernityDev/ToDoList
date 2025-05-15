package re.ethernity.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import re.ethernity.todolist.Entity.Priority;

public interface PriorityRepository extends CrudRepository<Priority, Long> {
}