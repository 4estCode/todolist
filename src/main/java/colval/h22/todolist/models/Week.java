package colval.h22.todolist.models;

import colval.h22.todolist.models.entities.User;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Data
public class Week {
    private Day sunday, monday, tuesday, wednesday, thursday, friday, saturday;

    private User user;

    public List<Day> getDays() {
        return Arrays.asList(
                sunday,
                monday,
                tuesday,
                wednesday,
                thursday,
                friday,
                saturday
                );
    }

    public static void GenerateWeekFromDate(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        System.out.println("Day of week : " + dayOfWeek);
    }
}