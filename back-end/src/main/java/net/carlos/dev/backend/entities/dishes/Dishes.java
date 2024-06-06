package net.carlos.dev.backend.entities.dishes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_dishes_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    private CategoryDishes categoryDishes;
    @OneToMany(mappedBy = "dishes" ,cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<PhotoDishes> photoDishes;
    @OneToMany(mappedBy = "dishes" ,cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<DishesOrders> dishesOrders;

}
