package net.carlos.dev.backend.controllers.users;

import net.carlos.dev.backend.dto.users.PersonaDTO;
import net.carlos.dev.backend.mappers.users.PersonaMapper;
import net.carlos.dev.backend.service.impl.users.PersonaServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gastro-tech/api/v1/users")
public class PersonaController {
    private static final Logger LOGGER = LogManager.getLogger(PersonaController.class);

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

    @PutMapping("/persona/update")
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

    @GetMapping("/persona/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.findById(id));
    }

    @GetMapping("/persona/identification/{identification}")
    public ResponseEntity<?> findByIdentification(@PathVariable Long identification) {
        return ResponseEntity.ok(personaService.findByIdentification(identification));
    }

    @GetMapping("/persona/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        return ResponseEntity.ok(personaService.findByFirstName(name));
    }

    @GetMapping("/persona/lastname/{lastName}")
    public ResponseEntity<?> findByLastName(@PathVariable String lastName) {
        return ResponseEntity.ok(personaService.findByFirstLastName(lastName));
    }

    @GetMapping("/persona/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(personaService.findByEmail(email));
    }

    @GetMapping("/persona/age/{age}")
    public ResponseEntity<?> findByAge(@PathVariable Integer age) {
        return ResponseEntity.ok(personaService.findByAge(age));
    }

    @GetMapping("/personasUsers")
    public ResponseEntity<?> findAllPersonasUsers() {
        return ResponseEntity.ok(personaService.findAllPersonasUsers());
    }

    @GetMapping("personasUsers/active")
    public ResponseEntity<?> findAllPersonasUsersActive() {
        return ResponseEntity.ok(personaService.findAllPersonasUsers());
    }

    @GetMapping("personasUsers/inactive")
    public ResponseEntity<?> findAllPersonasUsersInactive() {
        return ResponseEntity.ok(personaService.findAllPersonasUsersActive());
    }

    @GetMapping("personasUsers/username/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(personaService.findByUsername(username));
    }
}
