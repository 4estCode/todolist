package colval.h22.todolist.controllers;

import colval.h22.todolist.models.Week;
import colval.h22.todolist.models.dto.DateDTO;
import colval.h22.todolist.models.dto.ItemDTO;
import colval.h22.todolist.models.entities.Item;
import colval.h22.todolist.models.entities.ItemDate;
import colval.h22.todolist.services.DateService;
import colval.h22.todolist.services.ItemService;
import colval.h22.todolist.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class TodoController {
    private final ItemService itemService;
    private final UserService userService;
    private final DateService dateService;

    public TodoController(ItemService itemService, UserService userService, DateService dateService) {
        this.itemService = itemService;
        this.userService = userService;
        this.dateService = dateService;
    }

    @GetMapping()
    public String showIndex(Model model, Principal principal) {
        var user = userService.findByUsername(principal.getName()).orElseThrow();
        Week week = new Week();

        model.addAttribute("username", user.getUsername());

        if (!model.containsAttribute("week")) {
            week = Week.GenerateCurrentWeek();
            week = itemService.populateWeekWithUserTasks(week, user.getId());
            model.addAttribute("week", week);
        } else {
            week = (Week) model.getAttribute("week");
            week = itemService.populateWeekWithUserTasks(week, user.getId());
            model.addAttribute("week", week);
        }
        var localDate = week.getSunday().getLocalDate();

        var dto = new DateDTO(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()); // FIXME : Bad code
        model.addAttribute("dto", dto);
        return "pages/calendar/show";
    }

    @GetMapping("/user/profile")
    public String showProfile(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "pages/user/profile";
    }

    @GetMapping("/add?yearValue={yearValue}&monthValue={monthValue}&dayValue={dayValue}")
    public String add(@PathVariable int yearValue, @PathVariable int monthValue, @PathVariable int dayValue, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());

        var dateFound = dateService.getByDate(yearValue, monthValue, dayValue).orElseThrow();

        return addItem(dateFound, model, principal);
    }

    @GetMapping("/add")
    public String addItem(ItemDate date, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("date", date);
        model.addAttribute("userId", userService.findByUsername(principal.getName()));

        return "pages/calendar/add";
    }

    @PostMapping("/postAdd")
    public String addItem(@ModelAttribute("dto") ItemDTO itemDTO, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());

        itemService.createWithUserId(new Item(itemDTO), itemDTO.getUserId());

        return "redirect:/";
    }

    @PostMapping("/current")
    public String current(@ModelAttribute("dto") DateDTO dto, Model model, Principal principal) {
        var user = userService.findByUsername(principal.getName()).orElseThrow();
        Week week = Week.GenerateCurrentWeek();
        week = itemService.populateWeekWithUserTasks(week, user.getId());

        model.addAttribute("week", week);
        model.addAttribute("username", user.getUsername());
        return showIndex(model, principal);
    }

    @PostMapping("/next")
    public String nextWeek(@ModelAttribute("dto") DateDTO dto, Model model, Principal principal) {
        var user = userService.findByUsername(principal.getName()).orElseThrow();

        Week week = Week.GenerateNextWeek(dto);
        week = itemService.populateWeekWithUserTasks(week, user.getId());

        model.addAttribute("username", user.getUsername());
        model.addAttribute("week", week);
        return showIndex(model, principal);
    }

    @PostMapping("/previous")
    public String previousWeek(@ModelAttribute("dto") DateDTO dto, Model model, Principal principal) {
        var user = userService.findByUsername(principal.getName()).orElseThrow();

        LocalDate now = LocalDate.now();

        now.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

        Week week = Week.GeneratePreviousWeek(dto);
        week = itemService.populateWeekWithUserTasks(week, user.getId());

        model.addAttribute("username", user.getUsername());
        model.addAttribute("week", week);
        return showIndex(model, principal);
    }
}
