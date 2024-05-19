package net.carlos.dev.backend.entities.dishes;

import jakarta.persistence.*;
import lombok.Data;
import net.carlos.dev.backend.entities.DishesOrders;

import java.util.List;

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
    @JoinColumn(name = "category_dishes_id")
    private CategoryDishes categoryDishes;

    @OneToMany(mappedBy = "dishes" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotoDishes> photoDishes;

    @OneToMany(mappedBy = "dishes" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishesOrders> dishesOrders;

}
