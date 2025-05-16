package re.ethernity.todolist.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.ethernity.todolist.Entity.RecurrentTask;
import re.ethernity.todolist.repository.RecurrentTaskRepository;
import re.ethernity.todolist.Entity.Task;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recurrentTask/")
public class RecurrentTaskController {

    @Autowired
    private RecurrentTaskRepository recurrentTaskRepository;

    @GetMapping("/")
    public List<RecurrentTask> getAllTasks() {
        return (List<RecurrentTask>) recurrentTaskRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable long id) {
        Optional<RecurrentTask> task = recurrentTaskRepository.findById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cette tâche récurrente n'a pas été trouvée. ID -> " + id);
        }
    }

    @GetMapping("/status/{status}")
    public List<RecurrentTask> getByStatus(@PathVariable Task.Status status) {
        return recurrentTaskRepository.findByStatus(status);
    }



    @PostMapping("/")
    public RecurrentTask save(@RequestBody RecurrentTask recurrentTask) {
        return recurrentTaskRepository.save(recurrentTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable long id, @RequestBody RecurrentTask updatedTask) {
        Optional<RecurrentTask> taskOptional = recurrentTaskRepository.findById(id);
        if (taskOptional.isPresent()) {
            RecurrentTask task = taskOptional.get();
            task.setName(updatedTask.getName());
            task.setDescription(updatedTask.getDescription());
            task.setPriority(updatedTask.getPriority());
            task.setFrequency(updatedTask.getFrequency());
            task.setCategories(updatedTask.getCategories());

            recurrentTaskRepository.save(task);
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tâche récurrente non trouvée. ID -> " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable long id) {
        Optional<RecurrentTask> taskOptional = recurrentTaskRepository.findById(id);
        if (taskOptional.isPresent()) {
            recurrentTaskRepository.delete(taskOptional.get());
            return ResponseEntity.ok("Tâche récurrente supprimée avec succès. ID -> " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tâche récurrente non trouvée. ID -> " + id);
        }
    }
}
