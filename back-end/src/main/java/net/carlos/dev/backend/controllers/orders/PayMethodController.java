package net.carlos.dev.backend.controllers.orders;

import net.carlos.dev.backend.dto.payment.PaymentMethodDTO;
import net.carlos.dev.backend.service.impl.orders.PaymentMethodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gastro-tech/api/v1/orders")
public class PayMethodController {
    @Autowired
    private PaymentMethodServiceImpl payMethodService;

    @PostMapping("/pay-method")
    public boolean savePayMethod(@RequestBody PaymentMethodDTO paymentMethodDTO){
        return payMethodService.savePayMethod(paymentMethodDTO);
    }

    @PutMapping("/pay-method")
    public boolean updatePayMethod(@RequestBody PaymentMethodDTO paymentMethodDTO){
        return payMethodService.updatePayMethod(paymentMethodDTO);
    }

    @DeleteMapping("/pay-method/{id}")
    public boolean deletePayMethod(@PathVariable Long id){
        return payMethodService.deletePayMethod(id);
    }

    @GetMapping("/pay-method/{id}")
    public PaymentMethodDTO getPayMethodById(@PathVariable Long id){
        return payMethodService.getPayMethodById(id);
    }

    @GetMapping("/pay-method")
    public List<PaymentMethodDTO> getAllPayMethods(){
        return payMethodService.getAllPayMethods();
    }

    @GetMapping("/pay-method/{name}")
    public PaymentMethodDTO getPayMethodByName(@PathVariable String name){
        return payMethodService.getPayMethodByName(name);
    }
}
