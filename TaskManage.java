package com.codility.tasks.hibernate.solution;

import org.springframework.data.jpa.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.logging.Logger;
import java.util.Optional;

@Entity
@Table(name = "TASK")
class Task {

    @Id
    @Column
    private Long id;

    @Column
    private String description;

    @Column
    private Long priority;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }
}

@RestController
class TaskController {
  private static Logger log = Logger.getLogger("Solution");
  // log.info("You can use 'log' for debug messages");

  @Autowired
  private TaskService taskService;

  @PutMapping("/tasks/{id}")
  public ResponseEntity<Model> checkTasks(@PathVariable Long id, @RequestBody Request input) {
      log.info(">>> Check Tasks and Update ID");
      Response output = taskService.checkTasks(id, input);

      if(200 == output.getStatus()) {
          return ResponseEntity.status(output.getStatus()).body(input);
      }

      return ResponseEntity.status(output.getStatus()).body(output);
  }
}

@Service
class TaskServiceImpl implements TaskService {
    private static Logger log = Logger.getLogger("Solution Impl");

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Response checkTasks(Long id, Request input) {

        log.info("Check if description is present");
        Optional<String> optDescription = Optional.ofNullable(input.getDescription());
        if(!optDescription.isPresent()) {
            log.info("Task description is required 400");
            return new Response("Task description is required", 400);
        }

        Optional<Task> optTask = taskRepository.findById(id);

        log.info("Check if id is present");
        if(!optTask.isPresent()) {
            log.info("Cannot find task with given id 404");
            return new Response("Cannot find task with given id", 404);
        }

        log.info("Updated Task ID {}");
        Task task = optTask.get();
        task.setDescription(optDescription.get());
        task.setPriority(input.getPriority());

        taskRepository.save(task);
        return new Response("OK", 200);
    }
}

interface TaskService {
 Response checkTasks(Long id, Request input);
}

interface TaskRepository extends JpaRepository<Task, Long> {

}

class Response extends Model {
    private String message;
    private int status;

    public Response(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}

class Request extends Model {
    private String description;
    private Long priority;

    public String getDescription() {
        return description;
    }

    public Long getPriority() {
        return priority;
    }
}

class Model {}
