package colval.h22.todolist.repositories;

import colval.h22.todolist.models.Item;
import colval.h22.todolist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> getByCredentials(String username, String password);
}
