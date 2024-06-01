package net.carlos.dev.backend.controllers.dishes;

import net.carlos.dev.backend.entities.dishes.PhotoDishes;
import net.carlos.dev.backend.service.impl.dishes.PhotoDishesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gastro-tech/api/v1/dishes")
public class PhotoDishesController {

    @Autowired
    PhotoDishesServiceImpl photoDishesService;
    @PostMapping("/photoDish")
    public PhotoDishes save(@RequestBody PhotoDishes photoDishes) {
        return photoDishesService.save(photoDishes);
    }
    @PutMapping("/photoDish/update")
    public PhotoDishes update(@RequestBody PhotoDishes photoDishes) {
        return photoDishesService.update(photoDishes);
    }
    @DeleteMapping("/photoDish/delete/{id}")
    public void delete(@PathVariable Long id) {
        photoDishesService.delete(id);
    }
    @GetMapping("/photoDish/id/{id}")
    public PhotoDishes findById(@PathVariable Long id) {
        return photoDishesService.findById(id);
    }
}
