package ra.user_validate_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.user_validate_api.model.dto.request.UserUpdate;
import ra.user_validate_api.model.entity.User;
import ra.user_validate_api.repository.UserRepository;
import ra.user_validate_api.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new NoSuchElementException("User not found"));
    }

    @Override
    public User getUserByFullName(String fullName) {
        return userRepository.findByFullName(fullName).orElseThrow(()->new NoSuchElementException("User not found"));
    }

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdate userUpdate, Integer id) {
        User user = userRepository.findById(id).orElseThrow(()->new NoSuchElementException("User not found"));
        user = User.builder()
                .id(id)
                .address(userUpdate.getAddress())
                .email(userUpdate.getEmail())
                .fullName(userUpdate.getFullName())
                .password(userUpdate.getPassword())
                .phone(userUpdate.getPhone())
                .username(userUpdate.getUsername())
                .status(userUpdate.getStatus())
                .build();
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()->new NoSuchElementException("User not found"));
        user.setStatus(false);
        userRepository.save(user);
    }
}
