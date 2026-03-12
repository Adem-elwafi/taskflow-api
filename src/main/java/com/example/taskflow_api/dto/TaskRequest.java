package com.example.taskflow_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequest(
        @NotBlank(message = "Le titre ne peut pas être vide")
        @Size(min = 3, max = 50, message = "Le titre doit faire entre 3 et 50 caractères")
        String title,

        @Size(max = 255, message = "La description est trop longue")
        String description
) {}