package colval.h22.todolist.models;

import colval.h22.todolist.models.dto.ItemDTO;
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

    // RELATIONS

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "created_date_id")
    @ToString.Exclude
    private ItemDate created;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "deadline_date_id")
    @ToString.Exclude
    private ItemDate deadline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    @ToString.Exclude
    private User user;

    //

    public Item(ItemDTO dto) {
        this.title = dto.getTitle();
        this.percentWeightOnYear = dto.getPercentWeightOnYear();
        this.isTeamWork = dto.isTeamWork();
        this.className = dto.getClassName();
        // FIXME Date hardcoded
        this.created = new ItemDate(2022, 01, 01);
        this.deadline = dto.getDeadline();
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

