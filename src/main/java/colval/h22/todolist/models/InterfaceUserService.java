package colval.h22.todolist.models;

import java.util.List;

public interface InterfaceUserService {
    public User create(User user);

    public User read(Long id);

    public User update(User user);

    public User delete(Long id);

    public User addItemsToUser(Long id, List<Item> items);

    public Week getCurrentWeek(Long id);
}
