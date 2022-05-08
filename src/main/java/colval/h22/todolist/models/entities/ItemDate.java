package colval.h22.todolist.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "item_date")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "ItemDate.getByDate",
        query = "select d from ItemDate d where " +
                    "d.year = :year and " +
                    "d.month = :month and " +
                    "d.day = :day")
public class ItemDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Long id;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "day")
    private int day;

    // RELATION

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "deadline"
    )
    @ToString.Exclude
    @JsonManagedReference
    private List<Item> items;

    public ItemDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemDate itemDate = (ItemDate) o;
        return id != null && Objects.equals(id, itemDate.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
