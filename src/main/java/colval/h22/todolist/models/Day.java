package colval.h22.todolist.models;

import colval.h22.todolist.models.entities.Item;
import colval.h22.todolist.models.entities.User;
import lombok.*;
import java.util.List;

@Data
public class Day {
    private Integer number;

    private List<Item> todos;

    private User user;

    private Week week;
}