package colval.h22.todolist.models.entities;

import colval.h22.todolist.models.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "User.getByCredentials",
                query = "select u from User u where u.username = :username and u.password = :password"),
        @NamedQuery(
                name = "User.findByUsername",
                query = "select u from User u where u.username = :username")
})
public class User {

    // VARIABLES

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    // RELATIONS

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    @ToString.Exclude
    @JsonManagedReference
    private List<Item> items;

    //

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(UserDTO dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

