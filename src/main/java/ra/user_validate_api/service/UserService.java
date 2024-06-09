package ra.user_validate_api.service;

import ra.user_validate_api.model.dto.request.UserUpdate;
import ra.user_validate_api.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserByUsername(String username);
    User getUserByFullName(String fullName);
    User insertUser(User user);
    User updateUser(UserUpdate userUpdate,Integer id);
    void deleteUser(Integer id);
}
