package net.carlos.dev.backend.controllers.users;

import net.carlos.dev.backend.dto.users.PersonaDTO;
import net.carlos.dev.backend.model.StatusUpdate;
import net.carlos.dev.backend.service.impl.users.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gastro-tech/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    void createUser(PersonaDTO persona) {
        userService.save(persona);
    }

    @PutMapping("/update")
    void updateUser(PersonaDTO persona) {
        userService.save(persona);
    }

    @PutMapping("/activate/{id}")
    public boolean activateUser(@PathVariable Long id, @RequestBody StatusUpdate status) {
        userService.activateUser(id, status.getStatus());
        return true;
    }
}
