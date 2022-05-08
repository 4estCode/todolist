package colval.h22.todolist.models.interfaces;

import colval.h22.todolist.models.entities.User;
import colval.h22.todolist.models.Week;

import java.util.List;
import java.util.Optional;

public interface InterfaceUserService {
    User create(User user);

    User read(Long id);

    User update(User user);

    User delete(Long id);

    List<User> getAll();

    Optional<User> getByCredentials(String username, String password);

    Week getCurrentWeek(Long id);
}
