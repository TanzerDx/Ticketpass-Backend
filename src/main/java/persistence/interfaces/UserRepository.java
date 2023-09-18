package persistence.interfaces;

import persistence.entities.UserEntity;

import java.util.Optional;

public interface UserRepository {
    UserEntity add(UserEntity user);

    UserEntity getUser(long id);

    void deleteUser(long id);

}
