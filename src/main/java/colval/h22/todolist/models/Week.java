package colval.h22.todolist.models;

import colval.h22.todolist.models.dto.DateDTO;
import colval.h22.todolist.models.entities.User;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Data
public class Week {
    private Day sunday, monday, tuesday, wednesday, thursday, friday, saturday;

    private User user;

    public static Week GenerateCurrentWeek() {
        var year = Calendar.YEAR;
        var month = Calendar.MONTH;
        var day = Calendar.DAY_OF_MONTH;

        return GenerateWeekFromDate(new DateDTO(year, month, day));
    }

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

    public static Week GenerateWeekFromDate(DateDTO date) {
        System.out.println("Day of week : " + date.getDay());

        // How
        // date = 08/05/22
        // date.getDayOfWeek() == 1 (sunday), (0->6) or (1->7) ?
        // var daysBefore
        // var daysAfter
        // var week = daysBefore + date + daysAfter
        // return week

        return new Week();
    }
}