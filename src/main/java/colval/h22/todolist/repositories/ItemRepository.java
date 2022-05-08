package colval.h22.todolist.repositories;

import colval.h22.todolist.models.entities.Item;
import colval.h22.todolist.models.entities.ItemDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByDeadline(ItemDate deadline);
}
