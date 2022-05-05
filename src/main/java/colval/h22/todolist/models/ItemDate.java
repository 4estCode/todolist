package colval.h22.todolist.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item_date")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Long id;

    // TODO : Create id with year + month + day

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "day")
    private int day;

    // RELATION

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Item> items;

    public ItemDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
