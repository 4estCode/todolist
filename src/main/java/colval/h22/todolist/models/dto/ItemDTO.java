package colval.h22.todolist.models.dto;

import colval.h22.todolist.models.entities.ItemDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDTO {
    private long userId;
    private String title;
    private int percentWeightOnYear;
    private boolean isTeamWork;
    private String className;
    private ItemDate deadline;

    public ItemDTO(long userId, String title, int percentWeightOnYear, boolean isTeamWork, String className, ItemDate deadline) {
        this.userId = userId;
        this.title = title;
        this.percentWeightOnYear = percentWeightOnYear;
        this.isTeamWork = isTeamWork;
        this.className = className;
        this.deadline = deadline;
    }
}
