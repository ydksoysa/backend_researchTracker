package lk.ijse.cmjd.researchtracker.document;

import jakarta.persistence.*;
import lombok.*;
import lk.ijse.cmjd.researchtracker.project.Project;
import lk.ijse.cmjd.researchtracker.user.User;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private String title;
    private String description;
    private String urlOrPath;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;

    private LocalDateTime uploadedAt;
}

