package net.carlos.dev.backend.service.users;

import net.carlos.dev.backend.entities.users.Role;

public interface RoleService {
    Role createRole(String name);
}
