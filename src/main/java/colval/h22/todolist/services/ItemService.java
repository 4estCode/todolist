package colval.h22.todolist.services;

import colval.h22.todolist.models.Week;
import colval.h22.todolist.models.entities.Item;
import colval.h22.todolist.models.interfaces.InterfaceItemService;
import colval.h22.todolist.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService implements InterfaceItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;
    private final DateService dateService;

    public ItemService(ItemRepository itemRepository, UserService userService, DateService dateService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.dateService = dateService;
    }

    @Override
    public Item createWithUserId(Item item, Long userId) {
        var userFound = userService.read(userId);
        item.setUser(userFound);

        item.setDeadline(
                dateService.getOrCreate(
                        item.getDeadline().getYear(),
                        item.getDeadline().getMonth(),
                        item.getDeadline().getDay())
        );

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
    public Week populateWeekWithUserTasks(Week week, long userId) {
        var days = week.getDays();
        for (var day: days) {
            var dateFound = dateService.getByDate(
                    day.getLocalDate().getYear(),
                    day.getLocalDate().getMonthValue(),
                    day.getLocalDate().getDayOfMonth()
            );
            if (dateFound.isPresent()) {
                var todos = new ArrayList<>(dateFound.orElseThrow().getItems());
                day.setTodos(todos);
            }
        }

        return week;
    }
}
