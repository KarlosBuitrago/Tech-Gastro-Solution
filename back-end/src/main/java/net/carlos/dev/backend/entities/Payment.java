package net.carlos.dev.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_payment", nullable = false)
    private String datePayment;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "pay_method_id")
    private PaymentMethod paymentMethod;
}
