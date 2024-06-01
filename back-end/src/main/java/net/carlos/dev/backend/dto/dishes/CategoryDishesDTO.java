package net.carlos.dev.backend.dto.dishes;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class CategoryDishesDTO {
    private Long id;
    private String name;

}
