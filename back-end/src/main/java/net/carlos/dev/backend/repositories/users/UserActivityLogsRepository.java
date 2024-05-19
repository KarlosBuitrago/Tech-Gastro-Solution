package net.carlos.dev.backend.repositories.users;

import net.carlos.dev.backend.entities.users.UsersActivityLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityLogsRepository extends JpaRepository<UsersActivityLogs, Long> {
}
