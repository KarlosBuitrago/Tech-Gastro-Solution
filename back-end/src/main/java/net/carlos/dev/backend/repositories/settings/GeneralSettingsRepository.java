package net.carlos.dev.backend.repositories.settings;

import net.carlos.dev.backend.entities.settings.GeneralSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralSettingsRepository extends JpaRepository<GeneralSettings, String> {
}
