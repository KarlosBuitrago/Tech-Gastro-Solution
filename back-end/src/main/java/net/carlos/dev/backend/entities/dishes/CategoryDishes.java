package net.carlos.dev.backend.entities.dishes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category_dishes")
public class CategoryDishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nameCategory", nullable = false)
    private String name;
    @OneToMany(mappedBy = "categoryDishes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dishes> dishes;

}
