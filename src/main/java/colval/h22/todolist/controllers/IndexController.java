package colval.h22.todolist.controllers;

import colval.h22.todolist.models.Item;
import colval.h22.todolist.models.User;
import colval.h22.todolist.services.ItemService;
import colval.h22.todolist.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

@Controller
@RequestMapping
public class IndexController {
    private final ItemService itemService;
    private final UserService userService;

    public IndexController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping
    public String showIndex(Model model) {
        String welcome = "Bienvenue Demo Thymeleaf";
        model.addAttribute("welcome", welcome);
        return "pages/index";
    }

    private void populate() {
        Item item01 = new Item("Item01", 20, false, "Class01", Timestamp.from(Instant.now()));
        Item item02 = new Item("Item02", 20, false, "Class01", Timestamp.from(Instant.now()));
        Item item03 = new Item("Item03", 20, false, "Class02", Timestamp.from(Instant.now()));
        Item item04 = new Item("Item04", 20, false, "Class02", Timestamp.from(Instant.now()));
        User user01 = new User("User01", "Password01");
        User user02 = new User("User02", "Password02");
        user01 = userService.create(user01);
        user02 = userService.create(user02);
        itemService.createManyWithUserId(Arrays.asList(item01, item02), user01.getId());
        itemService.createManyWithUserId(Arrays.asList(item03, item04), user02.getId());
    }
}
