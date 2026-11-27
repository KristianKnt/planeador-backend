
package com.planeador.planificador.controller;

import com.planeador.planificador.exception.ResourceNotFoundException;
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

  // Consultar todas las tareas activas
  @GetMapping
  public ResponseEntity<List<Task>> findAll() {
    return ResponseEntity.ok(taskService.findActiveTasks());
  }

  // Consultar tareas archivadas
  @GetMapping("/archived")
  public ResponseEntity<List<Task>> findArchivedTasks() {
    return ResponseEntity.ok(taskService.findArchivedTasks());
  }

  // Consultar una tarea por ID
  @GetMapping("/{id}")
  public ResponseEntity<Task> findById(@PathVariable Long id) {
    Task task = taskService.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "No se encontró la tarea con ID: " + id
        ));

    return ResponseEntity.ok(task);
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

    Task updatedTask = taskService.update(id, task);

    return ResponseEntity.ok(updatedTask);
  }

  // Archivar una tarea
  @PatchMapping("/{id}/archive")
  public ResponseEntity<Task> archive(@PathVariable Long id) {
    Task archivedTask = taskService.archive(id);

    return ResponseEntity.ok(archivedTask);
  }

  // Restaurar una tarea archivada
  @PatchMapping("/{id}/restore")
  public ResponseEntity<Task> restore(@PathVariable Long id) {
    Task restoredTask = taskService.restore(id);

    return ResponseEntity.ok(restoredTask);
  }
}
