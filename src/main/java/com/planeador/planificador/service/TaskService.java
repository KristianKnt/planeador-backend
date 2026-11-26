
package com.planeador.planificador.service;

import com.planeador.planificador.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

  List<Task> findAll();

  Optional<Task> findById(Long id);

  Task save(Task task);

  Task update(Long id, Task task);

  void deleteById(Long id);
}
