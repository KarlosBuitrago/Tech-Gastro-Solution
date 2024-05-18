package net.carlos.dev.backend.mappers;

import net.carlos.dev.backend.dto.PayMethodDTO;
import net.carlos.dev.backend.entities.PayMethod;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayMethodMapper {
    PayMethodMapper INSTANCE = Mappers.getMapper(PayMethodMapper.class);

    PayMethodDTO toDTO(PayMethod payMethod);

    PayMethod toEntity(PayMethodDTO payMethodDTO);
}
