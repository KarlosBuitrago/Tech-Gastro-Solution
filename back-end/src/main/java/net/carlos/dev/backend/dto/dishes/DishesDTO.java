package net.carlos.dev.backend.dto.dishes;

import lombok.Builder;
import lombok.Data;
import net.carlos.dev.backend.entities.dishes.CategoryDishes;
import net.carlos.dev.backend.entities.dishes.PhotoDishes;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class DishesDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private CategoryDishesDTO categoryDishesDTO;
    private List<PhotoDishesDTO> photoDishesDTO;

}
