package net.carlos.dev.backend.service;

import net.carlos.dev.backend.dto.PayMethodDTO;

import java.util.List;

public interface IPayMethodService {
    boolean savePayMethod(PayMethodDTO payMethodDTO);
    boolean updatePayMethod(PayMethodDTO payMethodDTO);
    boolean deletePayMethod(Long id);
    PayMethodDTO getPayMethodById(Long id);
    List<PayMethodDTO> getAllPayMethods();
    PayMethodDTO getPayMethodByName(String name);
}
