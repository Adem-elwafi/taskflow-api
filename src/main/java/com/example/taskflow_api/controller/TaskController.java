package com.example.taskflow_api.controller;

import com.example.taskflow_api.dto.TaskRequest;
import com.example.taskflow_api.dto.TaskResponse;
import com.example.taskflow_api.model.Status;
import com.example.taskflow_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ÉTAPE 5 : Utilise TaskRequest en entrée et TaskResponse en sortie
    @PostMapping
    public TaskResponse create(@Valid @RequestBody TaskRequest request) {
        return taskService.createTask(request);
    }

    // Retourne maintenant une liste de DTOs au lieu des entités brutes
    @GetMapping
    public List<TaskResponse> getAll() {
        return taskService.getAllTasks();
    }

    // Mise à jour du statut qui retourne aussi un DTO
    @PatchMapping("/{id}/status")
    public TaskResponse updateStatus(@PathVariable Long id, @RequestParam Status status) {
        return taskService.updateStatus(id, status);
    }
}