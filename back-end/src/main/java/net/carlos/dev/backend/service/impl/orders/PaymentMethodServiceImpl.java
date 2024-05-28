package net.carlos.dev.backend.service.impl.orders;

import net.carlos.dev.backend.dto.payment.PaymentMethodDTO;
import net.carlos.dev.backend.entities.PaymentMethod;
import net.carlos.dev.backend.mappers.orders.PayMethodMapper;
import net.carlos.dev.backend.repositories.PaymentMethodRepository;
import net.carlos.dev.backend.service.orders.IPayMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("IPayMethodService")
public class PaymentMethodServiceImpl implements IPayMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    private PayMethodMapper payMethodMapper = PayMethodMapper.INSTANCE;

    @Override
    public boolean savePayMethod(PaymentMethodDTO paymentMethodDTO) {
        if (paymentMethodDTO != null) {
            PaymentMethod paymentMethod = payMethodMapper.toEntity(paymentMethodDTO);
            paymentMethodRepository.save(paymentMethod);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePayMethod(PaymentMethodDTO paymentMethodDTO) {
        if (paymentMethodDTO != null){
            PaymentMethod paymentMethod = payMethodMapper.toEntity(paymentMethodDTO);
            if (paymentMethodRepository.findById(paymentMethod.getId()).isPresent()){
                return false;
            }else {
                paymentMethodRepository.save(paymentMethod);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePayMethod(Long id) {
        if (paymentMethodRepository.findById(id).isPresent()){
            paymentMethodRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PaymentMethodDTO getPayMethodById(Long id) {
        if (paymentMethodRepository.findById(id).isPresent()){
            return payMethodMapper.toDTO(paymentMethodRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public List<PaymentMethodDTO> getAllPayMethods() {
        if (!paymentMethodRepository.findAll().isEmpty()){
            return Collections.singletonList(payMethodMapper.toDTO((PaymentMethod) paymentMethodRepository.findAll()));
        }
        return null;
    }

    @Override
    public PaymentMethodDTO getPayMethodByName(String name) {
        if (paymentMethodRepository.findByName(name) != null){
            return payMethodMapper.toDTO(paymentMethodRepository.findByName(name));
        }
        return null;
    }
}
