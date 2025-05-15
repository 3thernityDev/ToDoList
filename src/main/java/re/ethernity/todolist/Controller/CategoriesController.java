package re.ethernity.todolist.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.ethernity.todolist.Entity.Categories;
import re.ethernity.todolist.repository.CategoriesRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping("/")
    public List<Categories> getAllCategories() {
        return (List<Categories>) categoriesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoriesById(@PathVariable Long id) {
        Optional<Categories> categories = categoriesRepository.findById(id);
        if (categories.isPresent()) {
            return new ResponseEntity<>(categories.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categorie non trouvé. ID ->" + id);
        }
    }

    @PostMapping("/")
    public Categories addCategory(@RequestBody Categories categories) {
        return categoriesRepository.save(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Categories categories) {
        Optional<Categories> categoriesOptional = categoriesRepository.findById(id);
        if (categoriesOptional.isPresent()) {
            categoriesOptional.get().setName(categories.getName());
            categoriesRepository.save(categoriesOptional.get());
            return new ResponseEntity<>(categoriesOptional.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categorie non trouvé. ID ->" + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Optional<Categories> categoriesOptional = categoriesRepository.findById(id);
        if (categoriesOptional.isPresent()) {
            categoriesRepository.delete(categoriesOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Categorie supprimé. ID ->" + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categorie non trouvé. ID ->" + id);
        }
    }

}



