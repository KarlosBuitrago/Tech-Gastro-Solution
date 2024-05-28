package net.carlos.dev.backend.mappers.orders;

import net.carlos.dev.backend.dto.payment.PaymentMethodDTO;
import net.carlos.dev.backend.entities.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayMethodMapper {
    PayMethodMapper INSTANCE = Mappers.getMapper(PayMethodMapper.class);

    PaymentMethodDTO toDTO(PaymentMethod paymentMethod);

    PaymentMethod toEntity(PaymentMethodDTO paymentMethodDTO);
}
