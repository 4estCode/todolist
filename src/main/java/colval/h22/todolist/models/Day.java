package colval.h22.todolist.models;

import colval.h22.todolist.models.entities.Item;
import colval.h22.todolist.models.entities.User;
import lombok.*;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Data
public class Day {
    private LocalDate localDate;

    private List<Item> todos;

    public Day(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getMonthDisplayName() {
        return localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }

    public int getMonthValue() {
        return localDate.getMonthValue();
    }

    public int getDayValue() {
        return localDate.getDayOfMonth();
    }

    public int getYearValue() {
        return localDate.getYear();
    }
}