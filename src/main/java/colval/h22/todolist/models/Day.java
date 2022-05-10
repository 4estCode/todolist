package colval.h22.todolist.models;

import colval.h22.todolist.models.entities.Item;
import colval.h22.todolist.models.entities.User;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
public class Day {
    private LocalDate localDate;

    private List<Item> todos;

    public Day(LocalDate localDate) {
        this.localDate = localDate;
    }
}