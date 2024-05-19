package net.carlos.dev.backend.dto.payment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PaymentMethodDTO {
    private Long id;
    private String name;
    private PaymentDTO paymentDTO;
}
