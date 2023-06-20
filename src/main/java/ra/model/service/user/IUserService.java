package ra.model.service.user;

import ra.model.entity.User;
import ra.model.service.IGenericService;

public interface IUserService extends IGenericService<User,Long> {
    boolean existsByUserName(String userName);
    boolean exitsByEmail(String email);
    User saveOrUpdate(User user);
}
