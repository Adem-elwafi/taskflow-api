package com.example.taskflow_api.controller;

import com.example.taskflow_api.model.Task;
import com.example.taskflow_api.model.Status;
import com.example.taskflow_api.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Dit à Spring que chaque méthode retournera du JSON.
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.getAllTasks();
    }

    @PatchMapping("/{id}/status")
    public Task updateStatus(@PathVariable Long id, @RequestParam Status status) {
        return taskService.updateStatus(id, status);
    }
}
