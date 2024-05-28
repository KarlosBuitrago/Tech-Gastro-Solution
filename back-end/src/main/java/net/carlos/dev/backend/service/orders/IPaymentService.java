package net.carlos.dev.backend.service.orders;

import net.carlos.dev.backend.dto.payment.PaymentDTO;

import java.util.List;

public interface IPaymentService {
    boolean savePayment(PaymentDTO paymentDTO);
    boolean updatePayment(PaymentDTO paymentDTO);
    boolean deletePayment(Long id);
    PaymentDTO getPaymentById(Long id);
    List<PaymentDTO> getAllPayments();
    PaymentDTO getPaymentByOrderNumber(String orderNumber);
}
