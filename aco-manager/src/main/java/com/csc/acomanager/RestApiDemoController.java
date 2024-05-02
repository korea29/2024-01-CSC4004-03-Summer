package com.csc.acomanager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestApiDemoController {
    private final List<User> users = new ArrayList<User>();

    public RestApiDemoController() {
    }

    @GetMapping("/users")
    Iterable<User> getUsers() {
        return users;
    }

    @GetMapping("/users/{id}")
    Optional<User> getUser(@PathVariable String id) {
        for (User user : users) {
            if (id.equals(user.getId())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/users")
    User postUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    @PutMapping("/users/{id}")
    ResponseEntity<User> putUser(@PathVariable String id, @RequestBody User user) {
        int userIndex = -1;
        for (User u : users) {
            if (id.equals(u.getId())) {
                userIndex = users.indexOf(u);
                users.set(userIndex, user);
            }
        }

        return (userIndex == -1) ?
                new ResponseEntity<>(postUser(user), HttpStatus.CREATED) :
                new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable String id) {
        users.removeIf(user -> user.getId().equals(id));
    }

}
