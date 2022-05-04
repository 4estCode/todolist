package colval.h22.todolist.controllers;

import colval.h22.todolist.models.Day;
import colval.h22.todolist.services.ItemService;
import colval.h22.todolist.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("week")
public class WeekController {
    private final ItemService itemService;
    private final UserService userService;

    public WeekController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping("add")
    public String addItem() {
        return "pages/items/add";
    }

    @GetMapping("/{userId}")
    public List<Day> showCurrentWeek(@PathVariable int userId) {
        userService.showCurrentWeek(user_id);
    }
}
