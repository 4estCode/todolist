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

    @Column(name = "class_name")
    private String className;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "deadline")
    private Timestamp deadline;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gradedItem_id", nullable = true)
    private GradedItem gradedItem;

    public Item(String title, String className, Timestamp deadline) {
        this.title = title;
        this.className = className;
        this.deadline = deadline;
        this.created = Timestamp.from(Instant.now());
    }

    public Item(String title, String className) {
        this.title = title;
        this.className = className;
        this.created = Timestamp.from(Instant.now());

    }
}

