package business.Interfaces;

import domain.Objects.User;
import domain.UsersRelated.AddUserRequest;
import domain.UsersRelated.AddUserResponse;

public interface UsersUseCases {
    AddUserResponse addUser(AddUserRequest request);

    User getUser(long id);

    void deleteUser(long id);
}
