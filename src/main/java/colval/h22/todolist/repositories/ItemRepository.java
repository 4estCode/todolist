package colval.h22.todolist.repositories;

import colval.h22.todolist.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
