package re.ethernity.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import re.ethernity.todolist.Entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}