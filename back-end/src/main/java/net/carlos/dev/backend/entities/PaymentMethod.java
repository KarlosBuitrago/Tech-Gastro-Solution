package net.carlos.dev.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_payment_method", nullable = false, length = 50, unique = true)
    private String name;
    @OneToMany(mappedBy = "paymentMethod")
    private List<Payment> payments;
}
