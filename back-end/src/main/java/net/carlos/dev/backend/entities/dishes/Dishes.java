package net.carlos.dev.backend.entities.dishes;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dishes")
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price")
    private Double price;
    @ManyToOne
    private CategoryDishes categoryDishes;

}
