package colval.h22.todolist.models.interfaces;

import colval.h22.todolist.models.entities.Item;
import colval.h22.todolist.models.entities.ItemDate;

public interface InterfaceDateService {
    ItemDate create(ItemDate date);
    ItemDate getIfExists(ItemDate date);
    ItemDate update(ItemDate date);
    ItemDate read(Long id);

    ItemDate getOrCreate(int year, int month, int day);
}
