package colval.h22.todolist.models.interfaces;

import colval.h22.todolist.models.User;

import java.util.List;
import java.util.Optional;

public interface InterfaceUserService {
    public User create(User user);

    public User read(Long id);

    public User update(User user);

    public User delete(Long id);

    public List<User> getAll();

    public Optional<User> getByCredentials(String username, String password);
}
