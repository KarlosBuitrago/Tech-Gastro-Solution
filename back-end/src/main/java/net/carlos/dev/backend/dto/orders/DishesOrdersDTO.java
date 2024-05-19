package net.carlos.dev.backend.dto.orders;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.carlos.dev.backend.dto.dishes.DishesDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
public class DishesOrdersDTO {
    private Long id;
    private DishesDTO dishesDTO;
    private OrdersDTO ordersDTO;
    private String dateTimeOrder;
    private String dateTimeDelivery;
    private String status;
    private Double quantity;
    private String comment;

    public void calculateDateTimeOrder() {
        String dateTimeOrder = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.dateTimeOrder = dateTimeOrder;
    }
    public void calculateDateTimeDelivery() {
        String dateTimeDelivery = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.dateTimeDelivery = dateTimeDelivery;
    }

}
