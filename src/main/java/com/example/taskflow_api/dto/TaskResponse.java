package com.example.taskflow_api.dto;

import com.example.taskflow_api.model.Status;

public record TaskResponse(
        Long id,
        String title,
        String description,
        Status status
) {}