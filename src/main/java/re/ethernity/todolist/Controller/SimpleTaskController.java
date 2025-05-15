package re.ethernity.todolist.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.ethernity.todolist.Entity.SimpleTask;
import re.ethernity.todolist.repository.SimpleTaskRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/simpleTask/")
public class SimpleTaskController {
    @Autowired
    private SimpleTaskRepository simpleTaskRepository;

    @GetMapping("/")
    public List<SimpleTask> getAllTasks() {
        return (List<SimpleTask>) simpleTaskRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable long id) {
        Optional<SimpleTask> task = simpleTaskRepository.findById(id);
        if (task.isPresent()) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cette tâche n'as pas était trouvé. ID ->" + id);
        }

    }

    @PostMapping("/")
    public SimpleTask save(@RequestBody SimpleTask simpleTask) {
        return simpleTaskRepository.save(simpleTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable long id, @RequestBody SimpleTask simpleTask) {
        Optional<SimpleTask> task = simpleTaskRepository.findById(id);
        if (task.isPresent()) {
            task.get().setName(simpleTask.getName());
            task.get().setDescription(simpleTask.getDescription());
            task.get().setPriority(simpleTask.getPriority());
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cette tâche n'as pas était trouvé. ID ->" + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable long id) {
        Optional<SimpleTask> task = simpleTaskRepository.findById(id);
        if (task.isPresent()) {
            simpleTaskRepository.delete(task.get());
            return ResponseEntity.ok().body("Cette tâche a était supprimé avec succès. ID ->" + id);
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cette tâche n'as pas était trouvé. ID ->" + id);
        }
    }
}
