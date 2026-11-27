
package com.planeador.planificador.repository;

import com.planeador.planificador.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByArchivedFalse();

  List<Task> findByArchivedTrue();
}
