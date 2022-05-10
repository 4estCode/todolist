package colval.h22.todolist.models.interfaces;

import colval.h22.todolist.models.Week;
import colval.h22.todolist.models.entities.Item;

import java.sql.Date;
import java.util.List;

public interface InterfaceItemService {
    Item createWithUserId(Item item, Long userId);
    List<Item> createManyWithUserId(List<Item> items, Long userId);
    Item read(Long id);
    List<Item> getAll();
    Item update(Item item);
    Item delete(Long id);

    Week populateWeekWithUserTasks(Week week, long userId);
}
