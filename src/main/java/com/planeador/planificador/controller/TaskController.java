
package com.planeador.planificador.controller;

import com.planeador.planificador.model.Task;
import com.planeador.planificador.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  // Consultar todas las tareas
  @GetMapping
  public ResponseEntity<List<Task>> findAll() {
    return ResponseEntity.ok(taskService.findAll());
  }

  // Consultar una tarea por ID
  @GetMapping("/{id}")
  public ResponseEntity<Task> findById(@PathVariable Long id) {

    return taskService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  // Registrar una tarea
  @PostMapping
  public ResponseEntity<Task> save(@Valid @RequestBody Task task) {

    Task savedTask = taskService.save(task);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
  }

  // Actualizar una tarea
  @PutMapping("/{id}")
  public ResponseEntity<Task> update(
      @PathVariable Long id,
      @Valid @RequestBody Task task) {

    if (taskService.findById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Task updatedTask = taskService.update(id, task);

    return ResponseEntity.ok(updatedTask);
  }

  // Eliminar una tarea
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {

    if (taskService.findById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    taskService.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
