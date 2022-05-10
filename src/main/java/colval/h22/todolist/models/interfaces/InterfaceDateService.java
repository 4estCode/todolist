package colval.h22.todolist.models.interfaces;

import colval.h22.todolist.models.entities.Item;
import colval.h22.todolist.models.entities.ItemDate;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface InterfaceDateService {
    ItemDate create(ItemDate date);
    ItemDate getIfExists(ItemDate date);
    ItemDate update(ItemDate date);
    ItemDate read(Long id);
    Optional<ItemDate> getByDate(int year, int month, int day);

    ItemDate getOrCreate(int year, int month, int day);
}
