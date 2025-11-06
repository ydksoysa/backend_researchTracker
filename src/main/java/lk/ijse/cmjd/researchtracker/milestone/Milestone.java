package lk.ijse.cmjd.researchtracker.milestone;

import jakarta.persistence.*;
import lombok.*;
import lk.ijse.cmjd.researchtracker.project.Project;
import lk.ijse.cmjd.researchtracker.user.User;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Milestone {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private LocalDateTime createdAt;
}

