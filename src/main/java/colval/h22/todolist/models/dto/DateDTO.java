package colval.h22.todolist.models.dto;

import colval.h22.todolist.models.entities.ItemDate;
import lombok.Data;

@Data
public class DateDTO {
    private int year;
    private int month;
    private int day;
    public DateDTO(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
