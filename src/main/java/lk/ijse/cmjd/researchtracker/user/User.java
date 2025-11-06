package lk.ijse.cmjd.researchtracker.user;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String fullName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private LocalDateTime createdAt;
}

