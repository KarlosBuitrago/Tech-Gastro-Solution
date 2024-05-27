package net.carlos.dev.backend.mappers.settings;

import net.carlos.dev.backend.dto.settings.LanguageSettingsDTO;
import net.carlos.dev.backend.entities.settings.LanguageSettings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LanguageSettingsMapper {
    LanguageSettingsMapper INSTANCE = Mappers.getMapper(LanguageSettingsMapper.class);
    LanguageSettingsDTO toDTO(LanguageSettings languageSettings);
    LanguageSettings toEntity(LanguageSettingsDTO languageSettingsDTO);
}
