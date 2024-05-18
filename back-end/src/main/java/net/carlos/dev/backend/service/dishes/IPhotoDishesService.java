package net.carlos.dev.backend.service.dishes;

import net.carlos.dev.backend.entities.dishes.PhotoDishes;

public interface IPhotoDishesService {
    PhotoDishes save(PhotoDishes photoDishes);
    PhotoDishes update(PhotoDishes photoDishes);
    void delete(Long id);
    PhotoDishes findById(Long id);
}
