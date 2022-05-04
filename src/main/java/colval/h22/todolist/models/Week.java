package colval.h22.todolist.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "week")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "week_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sunday_id", nullable = true)
    @ToString.Exclude
    private Day sunday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monday_id", nullable = true)
    @ToString.Exclude
    private Day monday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tuesday_id", nullable = true)
    @ToString.Exclude
    private Day tuesday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wednesday_id", nullable = true)
    @ToString.Exclude
    private Day wednesday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thursday_id", nullable = true)
    @ToString.Exclude
    private Day thursday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friday_id", nullable = true)
    @ToString.Exclude
    private Day friday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saturday_id", nullable = true)
    @ToString.Exclude
    private Day saturday;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Week week = (Week) o;
        return id != null && Objects.equals(id, week.id);
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

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}