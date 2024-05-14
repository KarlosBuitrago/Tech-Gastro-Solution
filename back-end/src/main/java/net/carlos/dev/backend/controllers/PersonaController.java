package net.carlos.dev.backend.controllers;

import net.carlos.dev.backend.dto.PersonaDTO;
import net.carlos.dev.backend.service.impl.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gastro-tech/api/v1")
public class PersonaController {
    @Qualifier("IPersonaServices")
    PersonaServiceImpl personaService;

    public PersonaController(PersonaServiceImpl personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/persona")
    public ResponseEntity<?> save(@RequestBody PersonaDTO persona) {
        System.out.println(persona.getIdentification());
        return ResponseEntity.ok(personaService.save(persona));
    }
}
