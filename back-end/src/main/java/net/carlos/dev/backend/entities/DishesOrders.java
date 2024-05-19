package net.carlos.dev.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.carlos.dev.backend.entities.dishes.Dishes;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "dishes_orders")
public class DishesOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_dishes", referencedColumnName = "id", nullable = false)
    private Dishes dishes;
    @ManyToOne
    @JoinColumn(name = "id_orders", referencedColumnName = "id", nullable = false)
    private Orders orders;
    @Column(name = "date_time_order", nullable = false)
    private String dateTimeOrder;
    @Column(name = "date_time_delivery")
    private String dateTimeDelivery;
    @Column(name = "status", nullable = false, length = 50)
    private String status;
    @Column(name = "price_total", nullable = false)
    private Integer quantity;
    @Column(name = "comment", nullable = false)
    private String comment;
}