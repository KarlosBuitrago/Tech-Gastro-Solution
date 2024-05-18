package net.carlos.dev.backend.service.impl.users;

import net.carlos.dev.backend.entities.users.Role;
import net.carlos.dev.backend.repositories.users.RoleRepository;
import net.carlos.dev.backend.service.users.RoleService;
import org.springframework.stereotype.Service;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(String name) {
        Role role = new Role();
        role.setRoleName(name);
        return roleRepository.save(role);
    }
}
