package colval.h22.todolist.repositories;

import colval.h22.todolist.models.Item;
import colval.h22.todolist.models.ItemDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByDeadline(ItemDate deadline);
}
