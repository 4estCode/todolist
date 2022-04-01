package colval.h22.todolist.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "graded_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradedItem extends Item {

    @Column(name = "weightOnYear")
    private int percentWeightOnYear;

    @Column(name = "is_team_work")
    private boolean isTeamWork;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "gradedItem")
    @JoinColumn(name = "item_id")
    private Item item;

    public GradedItem(String title, String className, Timestamp deadline, int percentWeightOnYear, boolean isTeamWork) {
        super(title, className, deadline);
        this.percentWeightOnYear = percentWeightOnYear;
        this.isTeamWork = isTeamWork;
    }

    public GradedItem(String title, String className, int percentWeightOnYear, boolean isTeamWork) {
        super(title, className);
        this.percentWeightOnYear = percentWeightOnYear;
        this.isTeamWork = isTeamWork;
    }
}
