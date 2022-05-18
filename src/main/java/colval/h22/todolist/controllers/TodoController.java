package colval.h22.todolist.controllers;

import colval.h22.todolist.models.Week;
import colval.h22.todolist.models.dto.DateDTO;
import colval.h22.todolist.services.ItemService;
import colval.h22.todolist.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class TodoController {
    private final ItemService itemService;
    private final UserService userService;

    public TodoController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
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

        Week week = Week.GeneratePreviousWeek(dto);
        week = itemService.populateWeekWithUserTasks(week, user.getId());

        model.addAttribute("username", user.getUsername());
        model.addAttribute("week", week);
        return showIndex(model, principal);
    }
}
