package re.ethernity.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import re.ethernity.todolist.Entity.Service;

public interface ServiceRepository extends CrudRepository<Service, Long> {
}