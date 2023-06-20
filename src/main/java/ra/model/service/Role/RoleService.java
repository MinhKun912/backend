package ra.model.service.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dao.IRoleRepository;
import ra.model.entity.ERole;
import ra.model.entity.Roles;

import java.util.Optional;
@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository repository;
    @Override
    public Optional<Roles> findByRoleName(ERole roleName) {
        return repository.findByRoleName(roleName);
    }
}
