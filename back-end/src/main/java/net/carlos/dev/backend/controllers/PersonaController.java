package net.carlos.dev.backend.controllers;

import net.carlos.dev.backend.dto.PersonaDTO;
import net.carlos.dev.backend.mappers.PersonaMapper;
import net.carlos.dev.backend.service.impl.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gastro-tech/api/v1")
public class PersonaController {

    @Qualifier("IPersonaServices")
    private final PersonaServiceImpl personaService;

    private final UsuarioController usuarioController;
    private final PersonaMapper personaMapper = PersonaMapper.INSTANCE;

    public PersonaController(PersonaServiceImpl personaService, UsuarioController usuarioController) {
        this.personaService = personaService;
        this.usuarioController = usuarioController;
    }

    @PostMapping("/persona")
    public ResponseEntity<?> save(@RequestBody PersonaDTO personaDTO) {
        PersonaDTO persona = personaService.save(personaDTO);
        usuarioController.createUser(personaDTO);
        return ResponseEntity.ok(persona);
    }

//    @PostMapping("/persona")
//    public ResponseEntity<?> update(@RequestBody PersonaDTO persona) {
//        return ResponseEntity.ok(personaService.update(persona));
//    }

//    @PostMapping("/persona/id")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        return ResponseEntity.ok(personaService.delete(id));
//    }
//
//    @GetMapping("/personas")
//    public ResponseEntity<?> findAll() {
//        return ResponseEntity.ok(personaService.findAll());
//    }

}
