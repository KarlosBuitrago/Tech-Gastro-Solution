package net.carlos.dev.backend.dto.dishes;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PhotoDishesDTO {
    private Long id;
    private String url;
     private DishesDTO dishesDTO;
}
