package net.carlos.dev.backend.mappers;

import net.carlos.dev.backend.dto.TablesDTO;
import net.carlos.dev.backend.entities.Tables;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TablesMapper {
    TablesMapper INSTANCE = Mappers.getMapper(TablesMapper.class);

    TablesDTO toDTO(Tables tables);

    Tables toEntity(TablesDTO tablesDTO);
}
