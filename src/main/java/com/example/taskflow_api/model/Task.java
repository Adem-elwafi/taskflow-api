package com.example.taskflow_api.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING) // Stocke le nom (ex: "A_FAIRE") au lieu de l'index (0, 1, 2)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Magie JPA : S'exécute automatiquement avant l'insertion
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = Status.A_FAIRE; // Statut par défaut
    }

    // Magie JPA : S'exécute à chaque mise à jour
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}