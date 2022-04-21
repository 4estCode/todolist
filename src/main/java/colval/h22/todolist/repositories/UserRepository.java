package colval.h22.todolist.repositories;

import colval.h22.todolist.models.Item;
import colval.h22.todolist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
