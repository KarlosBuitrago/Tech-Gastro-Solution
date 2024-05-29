package net.carlos.dev.backend.controllers.users;

import net.carlos.dev.backend.dto.users.PersonaDTO;
import net.carlos.dev.backend.mappers.users.PersonaMapper;
import net.carlos.dev.backend.service.impl.users.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gastro-tech/api/v1/users")
public class PersonaController {


    @Qualifier("IPersonaServices")
    private final PersonaServiceImpl personaService;

    private final UserController usuarioController;

    private final PersonaMapper personaMapper = PersonaMapper.INSTANCE;

    public PersonaController(PersonaServiceImpl personaService, UserController usuarioController) {
        this.personaService = personaService;
        this.usuarioController = usuarioController;
    }

    @PostMapping("/persona")
    public ResponseEntity<?> save(@RequestBody PersonaDTO personaDTO) {
        PersonaDTO persona = personaService.save(personaDTO);
        usuarioController.createUser(persona);
        return ResponseEntity.ok(personaDTO);
    }

    @PutMapping("/persona")
    public ResponseEntity<?> update(@RequestBody PersonaDTO persona) {
        return ResponseEntity.ok(personaService.update(persona));
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.delete(id));
    }

    @GetMapping("/personas")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(personaService.findAll());
    }

    @GetMapping("/persona/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.findById(id));
    }

    @GetMapping("/persona/{identification}")
    public ResponseEntity<?> findByIdentification(@PathVariable Long identification) {
        return ResponseEntity.ok(personaService.findByIdentification(identification));
    }

    @GetMapping("/persona/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        return ResponseEntity.ok(personaService.findByFirstName(name));
    }

    @GetMapping("/persona/{lastName}")
    public ResponseEntity<?> findByLastName(@PathVariable String lastName) {
        return ResponseEntity.ok(personaService.findByFirstLastName(lastName));
    }

    @GetMapping("/persona/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(personaService.findByEmail(email));
    }

    @GetMapping("/persona/{age}")
    public ResponseEntity<?> findByAge(@PathVariable Integer age) {
        return ResponseEntity.ok(personaService.findByAge(age));
    }

    @GetMapping("personasUsers")
    public ResponseEntity<?> findAllPersonasUsers() {
        return ResponseEntity.ok(personaService.findAllPersonasUsers());
    }
    @GetMapping("personasUsers/active")
    public ResponseEntity<?> findAllPersonasUsersActive() {
        return ResponseEntity.ok(personaService.findAllPersonasUsers());
    }
}
