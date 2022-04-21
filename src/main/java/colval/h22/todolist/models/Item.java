package colval.h22.todolist.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "weightOnYear")
    private int percentWeightOnYear;

    @Column(name = "is_team_work")
    private boolean isTeamWork;

    @Column(name = "class_name")
    private String className;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "deadline")
    private Timestamp deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    public Item(String title, int percentWeightOnYear, boolean isTeamWork, String className, Timestamp deadline, User user) {
        this.title = title;
        this.percentWeightOnYear = percentWeightOnYear;
        this.isTeamWork = isTeamWork;
        this.className = className;
        this.created = Timestamp.from(Instant.now());
        this.deadline = deadline;
        this.user = user;
    }

    public Item(String title, int percentWeightOnYear, boolean isTeamWork, String className, Timestamp deadline) {
        this.title = title;
        this.percentWeightOnYear = percentWeightOnYear;
        this.isTeamWork = isTeamWork;
        this.className = className;
        this.created = Timestamp.from(Instant.now());
        this.deadline = deadline;
    }
}

