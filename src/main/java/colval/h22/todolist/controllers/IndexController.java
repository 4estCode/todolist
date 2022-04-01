package colval.h22.todolist.controllers;

import colval.h22.todolist.models.GradedItem;
import colval.h22.todolist.models.Item;
import colval.h22.todolist.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Controller
@RequestMapping
public class IndexController {
    private final ItemService itemService;

    public IndexController(ItemService itemService) {
        this.itemService = itemService;
//        populate();
    }

    @GetMapping
    public String showIndex(Model model) {
        String welcome = "Bienvenue Demo Thymeleaf";
        model.addAttribute("welcome", welcome);
        return "pages/index";
    }

    private void populate() {
        LocalDate date = LocalDate.parse("2005-08-16");
        Instant instant = date.atStartOfDay(ZoneOffset.UTC).toInstant();
        Timestamp timestamp = Timestamp.from(instant);
        itemService.create(
                new Item("Item 01", "Class 01", timestamp)
        );
        itemService.create(
                new GradedItem("Item 02", "Class 02" , 15, false)
        );
        itemService.create(
                new Item("Item 04", "Class 01")
        );
    }
}
