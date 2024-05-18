package net.carlos.dev.backend.entities.dishes;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "photo_dishes")
public class PhotoDishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url")
    private String url;
    @ManyToOne
    @JoinColumn(name = "dishes_id")
    private Dishes dishes;
}
