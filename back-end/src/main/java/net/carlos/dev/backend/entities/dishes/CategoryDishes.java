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
    @OneToMany(mappedBy = "categoryDishes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dishes> dishes;

}
