package colval.h22.todolist.models;

public interface InterfaceItemService {
    Item create(Item item);
    Item read(Long id);
    Item update(Item item);
    Item delete(Long id);
}
