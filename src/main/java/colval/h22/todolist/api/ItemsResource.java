package colval.h22.todolist.api;

import colval.h22.todolist.models.entities.Item;
import colval.h22.todolist.models.dto.ItemDTO;
import colval.h22.todolist.services.DateService;
import colval.h22.todolist.services.ItemService;
import colval.h22.todolist.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemsResource {
    private final ItemService itemService;
    private final UserService userService;
    private final DateService dateService;
    private final Logger logger;

    public ItemsResource(ItemService itemService, UserService userService, DateService dateService) {
        this.itemService = itemService;
        this.userService = userService;
        this.dateService = dateService;
        this.logger = LoggerFactory.getLogger(ItemsResource.class);
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@RequestBody ItemDTO dto) {
        logger.info("Post request to create item:" + dto.toString());

        var item = new Item(dto);

        item = itemService.createWithUserId(item, dto.getUserId());

        return ResponseEntity.ok(item);
    }

    @PutMapping("/update/{item_id}")
    public ResponseEntity<Item> updateItem(@RequestBody ItemDTO dto, @PathVariable long item_id) {
        logger.info("Update item:" + dto.toString());
        var item = new Item(dto);
        item.setId(item_id);
        item = itemService.update(item);

        return ResponseEntity.ok(item);
    }

    @GetMapping()
    public ResponseEntity<List<Item>> getItems() {
        var items = itemService.getAll();
        logger.info("Get request for " + items.size() + " items");
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Item> deleteItem(@RequestBody long itemId) {
        logger.info("Delete item:" + itemId);

        var item = itemService.delete(itemId);

        return ResponseEntity.ok(item);
    }
}
