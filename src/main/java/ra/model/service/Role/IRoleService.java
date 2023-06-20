package ra.model.service.Role;

import ra.model.entity.ERole;
import ra.model.entity.Roles;

import java.util.Optional;

public interface IRoleService {
    Optional<Roles> findByRoleName(ERole roleName);

}
