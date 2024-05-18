package net.carlos.dev.backend.service.impl;

import net.carlos.dev.backend.dto.PayMethodDTO;
import net.carlos.dev.backend.entities.PayMethod;
import net.carlos.dev.backend.mappers.PayMethodMapper;
import net.carlos.dev.backend.repositories.PayMethodRepository;
import net.carlos.dev.backend.service.IPayMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("IPayMethodService")
public class PayMethodServiceImpl implements IPayMethodService {

    @Autowired
    private PayMethodRepository payMethodRepository;

    private PayMethodMapper payMethodMapper = PayMethodMapper.INSTANCE;

    @Override
    public boolean savePayMethod(PayMethodDTO payMethodDTO) {
        if (payMethodDTO != null) {
            PayMethod payMethod = payMethodMapper.toEntity(payMethodDTO);
            payMethodRepository.save(payMethod);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePayMethod(PayMethodDTO payMethodDTO) {
        if (payMethodDTO != null){
            PayMethod payMethod = payMethodMapper.toEntity(payMethodDTO);
            if (payMethodRepository.findById(payMethod.getId()).isPresent()){
                return false;
            }else {
                payMethodRepository.save(payMethod);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePayMethod(Long id) {
        if (payMethodRepository.findById(id).isPresent()){
            payMethodRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PayMethodDTO getPayMethodById(Long id) {
        if (payMethodRepository.findById(id).isPresent()){
            return payMethodMapper.toDTO(payMethodRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public List<PayMethodDTO> getAllPayMethods() {
        if (payMethodRepository.findAll().size() > 0){
            return Collections.singletonList(payMethodMapper.toDTO((PayMethod) payMethodRepository.findAll()));
        }
        return null;
    }

    @Override
    public PayMethodDTO getPayMethodByName(String name) {
        if (payMethodRepository.findByName(name) != null){
            return payMethodMapper.toDTO(payMethodRepository.findByName(name));
        }
        return null;
    }
}
