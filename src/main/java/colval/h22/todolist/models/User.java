package colval.h22.todolist.models;

import colval.h22.todolist.models.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "User.getByCredentials",
        query = "select u from User u where u.username = :username and u.password = :password")
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

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
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
}

