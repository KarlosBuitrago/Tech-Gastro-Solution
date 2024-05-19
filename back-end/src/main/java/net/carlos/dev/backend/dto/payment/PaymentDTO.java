package net.carlos.dev.backend.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.carlos.dev.backend.dto.orders.OrdersDTO;

@Data
@AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private String datePayment;
    private OrdersDTO ordersDTO;
    private PaymentMethodDTO paymentMethodDTO;
}
