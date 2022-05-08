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

    @GetMapping("/week")
    public ResponseEntity<Week> getCurrentWeekForUser(@RequestBody long userId) {
        var week = Week.GenerateCurrentWeek();
        var user = userService.read(userId);

        return ResponseEntity.ok(week);
    }

    @GetMapping()
    public ResponseEntity<Week> getWeekByDayForUser(@RequestBody DateDTO dateDTO) {
        var week = Week.GenerateWeekFromDate(dateDTO);

        return ResponseEntity.ok(week);
    }
}
