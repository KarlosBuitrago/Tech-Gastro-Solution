package net.carlos.dev.backend.repositories.settings;

import net.carlos.dev.backend.entities.settings.LanguageSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageSettingsRepository extends JpaRepository<LanguageSettings, String> {
}
