package colval.h22.todolist.services;

import colval.h22.todolist.models.InterfaceItemService;
import colval.h22.todolist.models.Item;
import colval.h22.todolist.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService implements InterfaceItemService {
    private final ItemRepository itemRepository;
    private final UserService userService; // FIXME: Bad way?

    public ItemService(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

//    @Override
//    public Item create(Item item) {
//        return itemRepository.save(item);
//    }

    @Override
    public Item createWithUserId(Item item, Long userId) {
        var userFound = userService.read(userId);
        item.setUser(userFound);

        return itemRepository.save(item);
    }

    @Override
    public List<Item> createManyWithUserId(List<Item> items, Long userId) {
        var returnList = new ArrayList<Item>();
        for (var item :
                items) {
            returnList.add(createWithUserId(item, userId));
        }
        return returnList;
    }

    @Override
    public Item read(Long id) {
        return itemRepository.findById(id).orElseThrow();
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
}
