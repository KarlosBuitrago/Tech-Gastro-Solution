package net.carlos.dev.backend.service.impl.orders;

import net.carlos.dev.backend.dto.orders.OrdersDTO;
import net.carlos.dev.backend.dto.payment.PaymentDTO;
import net.carlos.dev.backend.entities.Payment;
import net.carlos.dev.backend.mappers.orders.OrdersMapper;
import net.carlos.dev.backend.mappers.orders.PaymentMapper;
import net.carlos.dev.backend.repositories.OrdersRepository;
import net.carlos.dev.backend.repositories.PaymentMethodRepository;
import net.carlos.dev.backend.repositories.PaymentRepository;
import net.carlos.dev.backend.service.orders.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("IPaymentService")
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private OrdersRepository ordersRepository;

    private PaymentMapper paymentMapper = PaymentMapper.INSTANCE;
    private OrdersMapper ordersMapper = OrdersMapper.INSTANCE;
    @Override
    public boolean savePayment(PaymentDTO paymentDTO) {
        if (paymentDTO == null){
            return false;
        }else {
            Payment payment = paymentMapper.toEntity(paymentDTO);
            payment.setPaymentMethod(paymentMethodRepository.findByName(paymentDTO.getPaymentMethodDTO().getName()));
            payment.setOrders(ordersRepository.findById(paymentDTO.getOrdersDTO().getId()).get());
            paymentRepository.save(payment);
            return true;
        }
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) {
        if (paymentDTO != null){
            Payment payment = paymentMapper.toEntity(paymentDTO);
            if (paymentRepository.findById(payment.getId()).isPresent()){
                return false;
            }else {
                paymentRepository.save(payment);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePayment(Long id) {
        if (paymentRepository.findById(id).isPresent()){
            paymentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        if (paymentRepository.findById(id).isPresent()){
            return paymentMapper.toDTO(paymentRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        if (!paymentRepository.findAll().isEmpty()){
            return List.of(paymentMapper.toDTO((Payment) paymentRepository.findAll()));
        }
        return null;
    }

    @Override
    public PaymentDTO getPaymentByOrderNumber(String orderNumber){
        OrdersDTO ordersDTO = ordersMapper.toDTO(ordersRepository.findByOrderNumber(orderNumber));
        Payment payment = paymentRepository.findByOrders_OrderNumber(ordersDTO.getOrderNumber());
        if (payment != null){
            return paymentMapper.toDTO(payment);
        }
        return null;
    }
}
