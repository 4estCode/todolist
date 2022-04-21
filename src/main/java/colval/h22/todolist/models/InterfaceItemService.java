package colval.h22.todolist.models;

import java.util.List;

public interface InterfaceItemService {
//    Item create(Item item);
    Item createWithUserId(Item item, Long userId);
    List<Item> createManyWithUserId(List<Item> items, Long userId);
//    List<Item> createMany(List<Item> items);
    Item read(Long id);
    Item update(Item item);
    Item delete(Long id);
}
