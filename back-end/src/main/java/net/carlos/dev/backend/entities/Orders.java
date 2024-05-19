package net.carlos.dev.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import net.carlos.dev.backend.entities.users.User;

import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "order_date")
    private String orderDate;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private Tables tables;
    @OneToOne(mappedBy = "orders")
    private Payment payment;
    @OneToMany(mappedBy = "orders")
    private List<DishesOrders> dishesOrders;
}
