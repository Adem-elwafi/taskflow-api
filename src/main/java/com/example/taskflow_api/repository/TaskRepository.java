package com.example.taskflow_api.repository;

import com.example.taskflow_api.model.Task;
import com.example.taskflow_api.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Une méthode personnalisée pour filtrer par statut
    List<Task> findByStatus(Status status);
}