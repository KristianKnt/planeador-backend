
package com.planeador.planificador.service.impl;

import com.planeador.planificador.model.Task;
import com.planeador.planificador.repository.TaskRepository;
import com.planeador.planificador.service.TaskService;
import com.planeador.planificador.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  public TaskServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  @Override
  public Optional<Task> findById(Long id) {
    return taskRepository.findById(id);
  }

  @Override
  public Task save(Task task) {
    return taskRepository.save(task);
  }


  @Override
  public Task update(Long id, Task task) {

    Task existingTask = taskRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "No se encontró la tarea con ID: " + id
        ));

    existingTask.setName(task.getName());
    existingTask.setDescription(task.getDescription());
    existingTask.setAssignedTo(task.getAssignedTo());
    existingTask.setDueDate(task.getDueDate());
    existingTask.setStatus(task.getStatus());

    return taskRepository.save(existingTask);
  }


  @Override
  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }

  @Override
  public List<Task> findActiveTasks() {
    return taskRepository.findByArchivedFalse();
  }

  @Override
  public List<Task> findArchivedTasks() {
    return taskRepository.findByArchivedTrue();
  }

  @Override
  public Task archive(Long id) {
    Task task = taskRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "No se encontró la tarea con ID: " + id
        ));

    task.setArchived(true);

    return taskRepository.save(task);
  }

  @Override
  public Task restore(Long id) {
    Task task = taskRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "No se encontró la tarea con ID: " + id
        ));

    task.setArchived(false);

    return taskRepository.save(task);
  }
}
