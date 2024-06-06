package net.carlos.dev.backend.dto.orders;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.carlos.dev.backend.dto.payment.PaymentDTO;
import net.carlos.dev.backend.dto.TablesDTO;
import net.carlos.dev.backend.dto.users.UserDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
public class OrdersDTO {
    private Long id;
    private String orderNumber;
    private String orderDate;
    private String status;
    private UserDTO userDTO;
    private TablesDTO tablesDTO;
    private PaymentDTO paymentDTO;
    private DishesOrdersDTO dishesOrdersDTO;

    public void setDateNow() {
        LocalDateTime now = LocalDateTime.now();
        String dateNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString();
        this.orderDate = dateNow;
    }

    public void calculateOrderNumber() {
        if (getOrderNumber() == null) {
            this.orderNumber = String.valueOf(1L); // Set to 1 if it's null
        } else {
            this.orderNumber = String.valueOf(Integer.parseInt(getOrderNumber()) + 1); // Increment by 1
        }
        String orderFormated = String.format("%05d", Integer.parseInt(this.orderNumber));
        this.orderNumber = orderFormated;
    }
}
