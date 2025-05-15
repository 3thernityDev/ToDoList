package re.ethernity.todolist.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.ethernity.todolist.Entity.Service;
import re.ethernity.todolist.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service/")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/")
    public List<Service> getAllServices() {
        return (List<Service>) serviceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Long id) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isPresent()) {
            return new ResponseEntity<>(service.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service non trouvé. ID -> " + id);
        }
    }

    @PostMapping("/")
    public Service addService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @RequestBody Service service) {
        Optional<Service> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            serviceOptional.get().setName(service.getName());
            serviceRepository.save(serviceOptional.get());
            return new ResponseEntity<>(serviceOptional.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service non trouvé. ID -> " + id);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        Optional<Service> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            serviceRepository.delete(serviceOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Service supprimé. ID ->" + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service non trouvé. ID -> " + id);
        }
    }


}
