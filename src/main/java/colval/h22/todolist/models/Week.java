package colval.h22.todolist.models;

import colval.h22.todolist.models.dto.DateDTO;
import colval.h22.todolist.models.entities.User;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Data
public class Week {
    private Day sunday, monday, tuesday, wednesday, thursday, friday, saturday;

    private User user;

    public static Week GenerateCurrentWeek() {
        LocalDate now = LocalDate.now();

        return GenerateWeekFromDate(
                new DateDTO(now.getYear(), now.getMonthValue(), now.getDayOfMonth())
        );
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
        Week week = new Week();

        LocalDate now = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
        TemporalField fieldISO = WeekFields.of(Locale.US).dayOfWeek();

        for (int i = 1; i <= 7; i++) {
            var thisDate = now.with(fieldISO, i);
            var thisDay = new Day(thisDate);
            switch (i) {
                case 1 -> week.setSunday(thisDay);
                case 2 -> week.setMonday(thisDay);
                case 3 -> week.setTuesday(thisDay);
                case 4 -> week.setWednesday(thisDay);
                case 5 -> week.setThursday(thisDay);
                case 6 -> week.setFriday(thisDay);
                case 7 -> week.setSaturday(thisDay);
            }
        }

        return week;
    }

    public static Week GeneratePreviousWeek(DateDTO date) {
        LocalDate now = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
        now = now.minus(1, ChronoUnit.WEEKS);
        date.setYear(now.getYear());
        date.setMonth(now.getMonthValue());
        date.setDay(now.getDayOfMonth());

        return GenerateWeekFromDate(date);
    }

    public static Week GenerateNextWeek(DateDTO date) {
        LocalDate now = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
        now = now.plus(1, ChronoUnit.WEEKS);
        date.setYear(now.getYear());
        date.setMonth(now.getMonthValue());
        date.setDay(now.getDayOfMonth());

        return GenerateWeekFromDate(date);
    }
}