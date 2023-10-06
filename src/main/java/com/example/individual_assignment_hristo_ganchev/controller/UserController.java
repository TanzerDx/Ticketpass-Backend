package com.example.individual_assignment_hristo_ganchev.controller;

import com.example.individual_assignment_hristo_ganchev.business.Interfaces.UsersService;
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

    private final UsersService usersService;

    @PostMapping()
    public ResponseEntity<AddUserResponse> addUser(@RequestBody AddUserRequest request) {
        AddUserResponse response = usersService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        final User user = usersService.getUser(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id)
    {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
