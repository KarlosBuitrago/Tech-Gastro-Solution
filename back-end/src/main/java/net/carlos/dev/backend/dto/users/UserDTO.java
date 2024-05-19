package net.carlos.dev.backend.dto.users;

import lombok.*;
import net.carlos.dev.backend.dto.orders.OrdersDTO;
import net.carlos.dev.backend.entities.users.Role;

import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {
    private PersonaDTO personaDTO;
    private String username;
    private String password;
    private Role role;
    private String status;
    private String apikey;
    private List<UsersActivityLogsDTO> usersActivityLogsDTO;
    private List<OrdersDTO> ordersDTO;

    public void createUsername(PersonaDTO personaDTO){
        String first = personaDTO.getFirstName().substring(0, 2);
        String last = personaDTO.getFirstLastName();
        this.username = first + last;
    }

    public void createPassword(PersonaDTO personaDTO){
        String first = personaDTO.getFirstName().substring(0, 2);
        String last = personaDTO.getFirstLastName().substring(0, 2);
        this.password = first + last + personaDTO.getIdentification();
    }

    public void createApiKey(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(30);
        for (int i = 0; i < 30; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }
        this.apikey = sb.toString();
    }
}
