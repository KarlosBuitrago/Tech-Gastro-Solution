package net.carlos.dev.backend.controllers;

import net.carlos.dev.backend.dto.PersonaDTO;
import net.carlos.dev.backend.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gastro-tech/api/v1/user")
public class UsuarioController {

    private final UserServiceImpl userService;

    public UsuarioController(UserServiceImpl userService) {
        this.userService = userService;
    }

    void createUser(PersonaDTO persona) {
        userService.save(persona);
    }
}
