package com.example.individual_assignment_hristo_ganchev.controller;

import com.example.individual_assignment_hristo_ganchev.business.Interfaces.UsersService;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.AddUserResponse;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginRequest;
import com.example.individual_assignment_hristo_ganchev.business.UsersRelated.LoginResponse;
import com.example.individual_assignment_hristo_ganchev.domain.User;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
//@CrossOrigin(origins = {"http://localhost:5173" , "http://localhost:4173"})
public class UserController {

    private final UsersService usersService;

    @PostMapping()
    public ResponseEntity<AddUserResponse> addUser(@RequestBody AddUserRequest request) {
        AddUserResponse response = usersService.addUser(request, "user");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @RolesAllowed({"manager"})
    @PostMapping(value = "admin")
    public ResponseEntity<AddUserResponse> addAdmin(@RequestBody AddUserRequest request) {
        AddUserResponse response = usersService.addUser(request, "admin");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "login")
    ResponseEntity<LoginResponse> Login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(usersService.Login(request));
    }


    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        final User user = usersService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "viaToken")
    public ResponseEntity<User> getUserByAccessToken(@RequestHeader("Authorization") String authorizationHeader) {

        String accessToken = extractAccessToken(authorizationHeader);

        final User user = usersService.getUserByAccessToken(accessToken);
        return ResponseEntity.ok().body(user);
    }

    private String extractAccessToken(String authorizationHeader) {
        String[] parts = authorizationHeader.split("\\s");
        if (parts.length == 2 && "Bearer".equals(parts[0])) {
            return parts[1];
        } else {
            throw new IllegalArgumentException("Invalid Authorization header format");
        }
    }

    @RolesAllowed({"admin", "manager"})
    @PutMapping("{id}")
    public ResponseEntity<Void> banUser(@PathVariable("id") Long id)
    {
        usersService.banUser(id);
        return ResponseEntity.noContent().build();
    }


    @RolesAllowed({"manager"})
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") Long id)
    {
        usersService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }


}
