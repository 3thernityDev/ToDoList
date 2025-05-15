package re.ethernity.todolist.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.ethernity.todolist.Entity.Priority;
import re.ethernity.todolist.repository.PriorityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/priority")
public class PriorityController {
    @Autowired
    private PriorityRepository priorityRepository;

    @GetMapping("/")
    public List<Priority> getPriority() {
        return (List<Priority>) priorityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPriorityById(@PathVariable Long id) {
        Optional<Priority> priority = priorityRepository.findById(id);
        if (priority.isPresent()) {
            return new ResponseEntity<>(priority.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La priorité n'as pas était trouvé. ID ->" + id);
        }
    }

    @PostMapping("/")
    public Priority addPriority(@RequestBody Priority priority) {
        return priorityRepository.save(priority);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePriority(@PathVariable Long id, @RequestBody Priority updatedPriority) {
        Optional<Priority> optionalPriority = priorityRepository.findById(id);

        if (optionalPriority.isPresent()) {
            Priority existingPriority = optionalPriority.get();
            existingPriority.setName(updatedPriority.getName());

            priorityRepository.save(existingPriority);
            return ResponseEntity.ok(existingPriority);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La priorité n'a pas été trouvée. ID -> " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePriority(@PathVariable Long id) {
        Optional<Priority> optionalPriority = priorityRepository.findById(id);
        if (optionalPriority.isPresent()) {
            priorityRepository.delete(optionalPriority.get());
            return ResponseEntity.ok().body("La priorité a était supprimé. ID -> " + id );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La priorité n'a pas été trouvé. ID -> " + id);
        }
    }


}
