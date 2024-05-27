package net.carlos.dev.backend.controllers.orders;

import net.carlos.dev.backend.dto.payment.PaymentMethodDTO;
import net.carlos.dev.backend.service.impl.PaymentMethodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gastro-tech/api/v1")
public class PayMethodController {
    @Autowired
    private PaymentMethodServiceImpl payMethodService;

    @PostMapping("/pay-method")
    public boolean savePayMethod(PaymentMethodDTO paymentMethodDTO){
        return payMethodService.savePayMethod(paymentMethodDTO);
    }

    @PutMapping("/pay-method")
    public boolean updatePayMethod(PaymentMethodDTO paymentMethodDTO){
        return payMethodService.updatePayMethod(paymentMethodDTO);
    }

    @DeleteMapping("/pay-method")
    public boolean deletePayMethod(Long id){
        return payMethodService.deletePayMethod(id);
    }

    @GetMapping("/pay-method/{id}")
    public PaymentMethodDTO getPayMethodById(Long id){
        return payMethodService.getPayMethodById(id);
    }

    @GetMapping("/pay-method")
    public List<PaymentMethodDTO> getAllPayMethods(){
        return payMethodService.getAllPayMethods();
    }

    @GetMapping("/pay-method/{name}")
    public PaymentMethodDTO getPayMethodByName(String name){
        return payMethodService.getPayMethodByName(name);
    }
}
