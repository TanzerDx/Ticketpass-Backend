package persistence.implementations;

import org.springframework.stereotype.Repository;
import persistence.entities.UserEntity;
import persistence.interfaces.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static long NEXT_ID = 1;

    private final List<UserEntity> savedUsers;

    public UserRepositoryImpl() {
        this.savedUsers = new ArrayList<>();
    }

    @Override
    public UserEntity add(UserEntity user)
    {
        user.setId(NEXT_ID);
        NEXT_ID++;
        this.savedUsers.add(user);
        return user;
    }

    @Override
    public UserEntity getUser(long id)
    {
        return savedUsers.stream()
                .filter(userEntity -> userEntity.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteUser(long id)
    {
        this.savedUsers.removeIf(userEntity -> userEntity.getId().equals(id));
    }

}
