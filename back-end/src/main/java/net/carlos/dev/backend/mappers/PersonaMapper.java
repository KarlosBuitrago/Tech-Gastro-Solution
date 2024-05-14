package net.carlos.dev.backend.mappers;

import net.carlos.dev.backend.dto.PersonaDTO;
import net.carlos.dev.backend.entities.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {
    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    PersonaDTO toDTO(Persona persona);

    Persona toEntity(PersonaDTO personaDTO);
}
