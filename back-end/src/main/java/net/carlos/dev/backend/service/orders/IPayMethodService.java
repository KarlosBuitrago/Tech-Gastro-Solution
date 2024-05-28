package net.carlos.dev.backend.service.orders;

import net.carlos.dev.backend.dto.payment.PaymentMethodDTO;

import java.util.List;

public interface IPayMethodService {
    boolean savePayMethod(PaymentMethodDTO paymentMethodDTO);
    boolean updatePayMethod(PaymentMethodDTO paymentMethodDTO);
    boolean deletePayMethod(Long id);
    PaymentMethodDTO getPayMethodById(Long id);
    List<PaymentMethodDTO> getAllPayMethods();
    PaymentMethodDTO getPayMethodByName(String name);
}
