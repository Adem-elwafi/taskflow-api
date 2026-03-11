package com.example.taskflow_api.service;

import com.example.taskflow_api.dto.TaskRequest;
import com.example.taskflow_api.dto.TaskResponse;
import com.example.taskflow_api.model.Task;
import com.example.taskflow_api.model.Status;
import com.example.taskflow_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // ÉTAPE 5 : Utilisation de TaskRequest pour la création
    public TaskResponse createTask(TaskRequest request) {
        // 1. MAPPING MANUEL : On transforme le DTO (Request) en Entité (Base de données)
        Task task = new Task();
        task.setTitle(request.title()); // On extrait la donnée du record
        task.setDescription(request.description());

        // 2. SAUVEGARDE : Le Repository enregistre l'entité dans WAMP/MySQL
        Task savedTask = taskRepository.save(task);

        // 3. MAPPING RETOUR : On transforme l'Entité sauvegardée en DTO (Response)
        return mapToResponse(savedTask);
    }

    public List<TaskResponse> getAllTasks() {
        // On récupère toutes les entités et on les transforme une par une en DTO
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse updateStatus(Long id, Status newStatus) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        // Logique métier (Workflow)
        if (task.getStatus() == Status.A_FAIRE && newStatus == Status.TERMINE) {
            throw new RuntimeException("Impossible de terminer une tâche qui n'a pas été commencée !");
        }

        task.setStatus(newStatus);
        Task updatedTask = taskRepository.save(task);

        return mapToResponse(updatedTask);
    }

    // MÉTHODE UTILITAIRE : Pour éviter de répéter le mapping partout
    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }
}