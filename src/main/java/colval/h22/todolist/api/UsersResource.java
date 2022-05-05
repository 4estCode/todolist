package colval.h22.todolist.api;

import colval.h22.todolist.models.User;
import colval.h22.todolist.models.dto.UserDTO;
import colval.h22.todolist.services.ItemService;
import colval.h22.todolist.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersResource {
    private final ItemService itemService;
    private final UserService userService;
    private final Logger logger;

    public UsersResource(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(UsersResource.class);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDTO dto) {
        logger.info("Post request to create user:" + dto.toString());

        var user = new User(dto);

        user = userService.create(user);

        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        logger.info("Get request for users");

        var users = userService.getAll();

        return ResponseEntity.ok(users);
    }

    @PostMapping("/connect")
    public ResponseEntity<User> connect(@RequestBody UserDTO dto) {
        logger.info("Connection request for " + dto.getUsername() + "...");

        var user = userService.getByCredentials(dto.getUsername(), dto.getPassword());

        if (user.isPresent()) {
            logger.info("Connection accepted...");
            return ResponseEntity.ok(user.orElseThrow());
        } else {
            logger.info("Connection refused...");
            return ResponseEntity.status(401).build();
        }
    }
}
