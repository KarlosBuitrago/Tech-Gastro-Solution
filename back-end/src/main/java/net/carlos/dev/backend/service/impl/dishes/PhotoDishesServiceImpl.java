package net.carlos.dev.backend.service.impl.dishes;

import net.carlos.dev.backend.entities.dishes.PhotoDishes;
import net.carlos.dev.backend.repositories.dishes.PhotoDishesRepository;
import net.carlos.dev.backend.service.dishes.IPhotoDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IPhotoDishesService")
public class PhotoDishesServiceImpl implements IPhotoDishesService {

    @Autowired
    private PhotoDishesRepository photoDishesRepository;

    @Override
    public PhotoDishes save(PhotoDishes photoDishes) {
        return photoDishesRepository.save(photoDishes);
    }

    @Override
    public PhotoDishes update(PhotoDishes photoDishes) {
        return photoDishesRepository.save(photoDishes);
    }

    @Override
    public void delete(Long id) {
        photoDishesRepository.deleteById(id);
    }

    @Override
    public PhotoDishes findById(Long id) {
        return photoDishesRepository.findById(id).orElse(null);
    }
}
