
package com.planeador.planificador.repository;

import com.planeador.planificador.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
