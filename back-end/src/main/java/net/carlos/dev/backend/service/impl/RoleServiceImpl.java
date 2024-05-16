package net.carlos.dev.backend.service.impl;

import net.carlos.dev.backend.entities.Role;
import net.carlos.dev.backend.repositories.RoleRepository;
import net.carlos.dev.backend.service.RoleService;
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
