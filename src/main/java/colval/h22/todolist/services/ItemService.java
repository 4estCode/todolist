package colval.h22.todolist.services;

import colval.h22.todolist.models.interfaces.InterfaceItemService;
import colval.h22.todolist.models.Item;
import colval.h22.todolist.repositories.ItemRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class ItemService implements InterfaceItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;

    public ItemService(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    @Override
    public Item createWithUserId(Item item, Long userId) {
        var userFound = userService.read(userId);
        item.setUser(userFound);

        return itemRepository.save(item);
    }

    @Override
    public List<Item> createManyWithUserId(List<Item> items, Long userId) {
        var userFound = userService.read(userId);

        for (var item : items)
            item.setUser(userFound);

        itemRepository.saveAll(items);
        return items;
    }

    @Override
    public Item read(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item update(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item delete(Long id) {
        var itemFound = read(id);
        itemRepository.delete(itemFound);
        return itemFound;
    }

    @Override
    public List<Item> getByDate(Date date) {
        throw new NotYetImplementedException();
    }
}
