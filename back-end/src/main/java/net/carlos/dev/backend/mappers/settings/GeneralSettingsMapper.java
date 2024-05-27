package net.carlos.dev.backend.mappers.settings;

import net.carlos.dev.backend.dto.settings.GeneralSettingsDTO;
import net.carlos.dev.backend.entities.settings.GeneralSettings;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GeneralSettingsMapper {
    GeneralSettingsMapper INSTANCE = Mappers.getMapper(GeneralSettingsMapper.class);
    GeneralSettingsDTO toDTO(GeneralSettings generalSettings);
    GeneralSettings toEntity(GeneralSettingsDTO generalSettingsDTO);
}
