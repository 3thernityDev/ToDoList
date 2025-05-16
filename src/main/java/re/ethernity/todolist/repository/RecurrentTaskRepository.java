package re.ethernity.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import re.ethernity.todolist.Entity.RecurrentTask;
import re.ethernity.todolist.Entity.Task;

import java.util.List;

public interface RecurrentTaskRepository extends CrudRepository<RecurrentTask, Long> {
    List<RecurrentTask> findByStatus(Task.Status attr0);
}