package colval.h22.todolist.models.entities;

import colval.h22.todolist.models.dto.ItemDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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

    @Column(name = "weight_on_year")
    private int percentWeightOnYear;

    @Column(name = "is_team_work")
    private boolean isTeamWork;

    @Column(name = "class_name")
    private String className;

    // RELATIONS

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "date_id")
    @ToString.Exclude
    @JsonBackReference
    private ItemDate deadline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonBackReference
    private User user;

    //

    public Item(ItemDTO dto) {
        this.title = dto.getTitle();
        this.percentWeightOnYear = dto.getPercentWeightOnYear();
        this.isTeamWork = dto.isTeamWork();
        this.className = dto.getClassName();
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

