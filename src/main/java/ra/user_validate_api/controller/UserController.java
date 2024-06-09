package ra.user_validate_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.user_validate_api.model.dto.request.UserUpdate;
import ra.user_validate_api.model.entity.User;
import ra.user_validate_api.service.UserService;
import ra.user_validate_api.validation.UserNameExist;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@Valid @PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username),HttpStatus.OK);
    }

    @GetMapping("/fullname/{fullname}")
    public ResponseEntity<User> getUserByFullname(@Valid @PathVariable String fullname) {
        return new ResponseEntity<>(userService.getUserByFullName(fullname),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.insertUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @PathVariable Integer id, @RequestBody UserUpdate userUpdate) {
        return new ResponseEntity<>(userService.updateUser(userUpdate, id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@Valid @PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
