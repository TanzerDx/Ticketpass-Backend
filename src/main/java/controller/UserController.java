package controller;

import business.Interfaces.UsersUseCases;
import domain.Objects.User;
import domain.UsersRelated.AddUserRequest;
import domain.UsersRelated.AddUserResponse;
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
    public ResponseEntity<User> getUser(@RequestParam(name = "id", required = false) final long id) {
        final User user = usersUseCases.getUser(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id)
    {
        usersUseCases.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
