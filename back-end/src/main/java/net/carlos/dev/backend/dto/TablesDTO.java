package net.carlos.dev.backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.carlos.dev.backend.dto.orders.OrdersDTO;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TablesDTO {
    private Long id;
    private String name;
    private String description;
    private List<OrdersDTO> ordersDTO;
}
