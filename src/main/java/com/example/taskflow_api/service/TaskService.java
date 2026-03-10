package com.example.taskflow_api.service;

import com.example.taskflow_api.model.Task;
import com.example.taskflow_api.model.Status;
import com.example.taskflow_api.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        // Le statut par défaut est déjà géré par @PrePersist dans l'entité
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateStatus(Long id, Status newStatus) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        // Petite logique métier : validation du workflow
        if (task.getStatus() == Status.A_FAIRE && newStatus == Status.TERMINE) {
            throw new RuntimeException("Impossible de terminer une tâche qui n'a pas été commencée !");
        }

        task.setStatus(newStatus);
        return taskRepository.save(task);
    }
}