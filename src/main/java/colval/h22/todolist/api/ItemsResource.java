package colval.h22.todolist.api;

import colval.h22.todolist.models.Item;
import colval.h22.todolist.models.ItemDate;
import colval.h22.todolist.models.User;
import colval.h22.todolist.models.dto.ItemDTO;
import colval.h22.todolist.models.dto.UserDTO;
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
    private final Logger logger;

    public ItemsResource(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(ItemsResource.class);
    }



    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@RequestBody ItemDTO dto) {
        logger.info("Post request to create item:" + dto.toString());

        var item = new Item(dto);

        item = itemService.createWithUserId(item, dto.getUserId());

        return ResponseEntity.ok(item);
    }

    @GetMapping()
    public ResponseEntity<List<Item>> getItems() {
        var items = itemService.getAll();
        logger.info("Get request for " + items.size() + " items");
        return ResponseEntity.ok(items);
    }

    @GetMapping("/test")
    public ResponseEntity<List<Item>> test() {
        var items = itemService.getAll();
        var dto = new ItemDTO(1, "title", 20, true, "class", new ItemDate(2022, 02, 02));
        var item = new Item(dto);
        logger.info(itemService.createWithUserId(item, dto.getUserId()).toString());
        return ResponseEntity.ok(items);
    }
}
