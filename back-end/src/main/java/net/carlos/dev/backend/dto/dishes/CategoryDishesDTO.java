package net.carlos.dev.backend.dto.dishes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CategoryDishesDTO {
    private Long id;
    private String name;
    private List<DishesDTO> dishesDTO;
}
