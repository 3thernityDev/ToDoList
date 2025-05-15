package re.ethernity.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import re.ethernity.todolist.Entity.RecurrentTask;

public interface RecurrentTaskRepository extends CrudRepository<RecurrentTask, Long> {
}