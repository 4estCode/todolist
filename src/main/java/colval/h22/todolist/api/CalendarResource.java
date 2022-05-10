package colval.h22.todolist.api;

import colval.h22.todolist.models.Week;
import colval.h22.todolist.models.dto.DateDTO;
import colval.h22.todolist.models.dto.UserDTO;
import colval.h22.todolist.models.entities.Item;
import colval.h22.todolist.models.entities.ItemDate;
import colval.h22.todolist.services.ItemService;
import colval.h22.todolist.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarResource {
    private final ItemService itemService;
    private final UserService userService;
    private final Logger logger;

    public CalendarResource(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(CalendarResource.class);
    }

    @GetMapping("/week/{user_id}")
    public ResponseEntity<Week> getCurrentWeekForUser(@PathVariable long user_id) {
        var week = Week.GenerateCurrentWeek();
        week = itemService.populateWeekWithUserTasks(week, user_id);

        return ResponseEntity.ok(week);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<Week> getWeekByDayForUser(@RequestBody DateDTO dateDTO, @PathVariable long user_id) {
        var week = Week.GenerateWeekFromDate(dateDTO);
        week = itemService.populateWeekWithUserTasks(week, user_id);

        return ResponseEntity.ok(week);
    }

    @GetMapping("/week/previous/{user_id}")
    public ResponseEntity<Week> getPreviousWeekForUser(@RequestBody DateDTO dateDTO, @PathVariable long user_id) {
        var week = Week.GeneratePreviousWeek(dateDTO);
        week = itemService.populateWeekWithUserTasks(week, user_id);

        return ResponseEntity.ok(week);
    }

    @GetMapping("/week/next/{user_id}")
    public ResponseEntity<Week> getNextWeekForUser(@RequestBody DateDTO dateDTO, @PathVariable long user_id) {
        var week = Week.GenerateNextWeek(dateDTO);
        week = itemService.populateWeekWithUserTasks(week, user_id);

        return ResponseEntity.ok(week);
    }
}
