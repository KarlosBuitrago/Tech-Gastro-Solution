package net.carlos.dev.backend.repositories.users;

import net.carlos.dev.backend.entities.users.Persona;
import net.carlos.dev.backend.entities.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Persona findByIdentification(Long identification);
    Persona findByFirstName(String firstName);
    Persona findByFirstLastName(String firstLastName);
    Persona findByAge(Integer age);
    Persona findByEmail(String email);
    @Query("SELECT p, u FROM Persona p JOIN p.user u WHERE u.username = ?1")
    Persona findByUsername(String username);

    @Query("SELECT p, u FROM Persona p JOIN p.user u")
    List<Object[]> findAllWithUser();

    @Query("SELECT p, u FROM Persona p JOIN p.user u WHERE u.status = 'Activo'")
    List<Object[]> findAllWithActiveUser();

    @Query("SELECT p, u FROM Persona p JOIN p.user u WHERE u.status = 'Inactivo'")
    List<Object[]> findAllWithInActiveUser();


}
