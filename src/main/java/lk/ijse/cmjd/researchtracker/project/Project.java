package lk.ijse.cmjd.researchtracker.project;

import jakarta.persistence.*;
import lombok.*;
import lk.ijse.cmjd.researchtracker.user.User;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    private String id;

    private String title;
    private String summary;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToOne
    @JoinColumn(name = "pi_id")
    private User pi;

    private String tags;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

