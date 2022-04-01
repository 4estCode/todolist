package colval.h22.todolist.services;

import colval.h22.todolist.models.InterfaceItemService;
import colval.h22.todolist.models.Item;
import colval.h22.todolist.repositories.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements InterfaceItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item create(Item item) {
        return itemRepository.save(item);
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
