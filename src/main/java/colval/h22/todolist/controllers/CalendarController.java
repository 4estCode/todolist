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
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    private final ItemService itemService;
    private final UserService userService;

    public CalendarController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping
    public String showIndex(Model model) {
        var days = new ArrayList<Integer>();
        for (int i = 1; i <= 14; i++) {
            days.add(i);
        }
        model.addAttribute("days", days);
        return "pages/calendar/show";
    }
}
