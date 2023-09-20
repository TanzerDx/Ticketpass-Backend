package com.example.individual_assignment_hristo_ganchev.controller;

import com.example.individual_assignment_hristo_ganchev.business.Interfaces.UsersUseCases;
import com.example.individual_assignment_hristo_ganchev.domain.Objects.User;
import com.example.individual_assignment_hristo_ganchev.domain.UsersRelated.AddUserRequest;
import com.example.individual_assignment_hristo_ganchev.domain.UsersRelated.AddUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UsersUseCases usersUseCases;

    @PostMapping()
    public ResponseEntity<AddUserResponse> addUser(@RequestBody AddUserRequest request) {
        AddUserResponse response = usersUseCases.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        final User user = usersUseCases.getUser(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id)
    {
        usersUseCases.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
