package com.example.taskflow_api.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskRequest(
        @NotBlank(message = "Le titre est requis") String title,
        String description
) {}