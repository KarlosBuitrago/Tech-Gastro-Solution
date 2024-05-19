package net.carlos.dev.backend.mappers;

import net.carlos.dev.backend.dto.payment.PaymentDTO;
import net.carlos.dev.backend.entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
    PaymentDTO toDTO(Payment payment);
    Payment toEntity(PaymentDTO paymentDTO);
}
