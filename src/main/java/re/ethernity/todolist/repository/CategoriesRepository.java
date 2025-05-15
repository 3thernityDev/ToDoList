package re.ethernity.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import re.ethernity.todolist.Entity.Categories;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {
}