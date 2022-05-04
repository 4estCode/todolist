package colval.h22.todolist.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    // VARIABLES

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

    // RELATIONS

    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id", nullable = true)
    @ToString.Exclude
    private Week week;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    @ToString.Exclude
    private User user;

    //

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Item item = (Item) o;
        return id != null && Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

