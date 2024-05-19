package net.carlos.dev.backend.entities.users;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.carlos.dev.backend.dto.users.UserDTO;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "users_activity_logs")
public class UsersActivityLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "activity")
    private String activity;
    @Column(name = "activity_date_time")
    private String activityDateTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
