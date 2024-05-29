package net.carlos.dev.backend.service.impl.orders;

import net.carlos.dev.backend.dto.orders.OrdersDTO;
import net.carlos.dev.backend.dto.TablesDTO;
import net.carlos.dev.backend.entities.Orders;
import net.carlos.dev.backend.entities.users.User;
import net.carlos.dev.backend.mappers.OrdersMapper;
import net.carlos.dev.backend.mappers.TablesMapper;
import net.carlos.dev.backend.mappers.users.PersonaMapper;
import net.carlos.dev.backend.mappers.users.UserMapper;
import net.carlos.dev.backend.repositories.OrdersRepository;
import net.carlos.dev.backend.repositories.TablesRepository;
import net.carlos.dev.backend.repositories.users.PersonaRepository;
import net.carlos.dev.backend.repositories.users.UserRepository;
import net.carlos.dev.backend.service.orders.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IOrdersService")
public class IOrdersServiceImpl implements IOrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TablesRepository tablesRepository;
    @Autowired
    private PersonaRepository personaRepository;

    private OrdersMapper ordersMapper = OrdersMapper.INSTANCE;
    private UserMapper userMapper = UserMapper.INSTANCE;
    private TablesMapper tablesMapper = TablesMapper.INSTANCE;
    private PersonaMapper personaMapper = PersonaMapper.INSTANCE;

    @Override
    public boolean saveOrder(OrdersDTO orderDTO) {
        TablesDTO tablesDTO = orderDTO.getTablesDTO();
        orderDTO.setDateNow();
        orderDTO.setTablesDTO(tablesDTO);
        Orders orders = ordersMapper.toEntity(orderDTO);
        User user = userRepository.findByUsername(orderDTO.getUserDTO().getUsername());
        orders.setUser(user);
        orders.setTables(tablesMapper.toEntity(tablesDTO));
        ordersRepository.save(orders);
        return true;
    }

    @Override
    public boolean updateOrder(OrdersDTO orderDTO) {
        if (orderDTO != null || orderDTO.getId() != null) {
            Orders orders = ordersMapper.toEntity(orderDTO);
            if (ordersRepository.findById(orders.getId()).isEmpty() || orders.getId().equals(orderDTO.getId())) {
                return false;
            } else {
                ordersRepository.save(orders);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteOrder(Long id) {
        if (ordersRepository.findById(id).isPresent()) {
            ordersRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<OrdersDTO> getAllOrders() {
        return ordersRepository.findAll().stream().map(ordersMapper::toDTO).toList();
    }

    @Override
    public OrdersDTO getOrderByOrderNumber(String orderNumber) {
        Orders orders = ordersRepository.findByOrderNumber(orderNumber);
        return ordersMapper.toDTO(orders);
    }

    @Override
    public List<OrdersDTO> getOrdersByUsername(String username) {
        List<Orders> orders = ordersRepository.findByUserUsername(username);
        return orders.stream().map(ordersMapper::toDTO).toList();
    }

    @Override
    public List<OrdersDTO> getOrdersByStatus(String status) {
        List<Orders> orders = ordersRepository.findByStatus(status);
        return orders.stream().map(ordersMapper::toDTO).toList();
    }
}
