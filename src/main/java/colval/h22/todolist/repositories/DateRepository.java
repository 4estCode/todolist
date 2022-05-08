package colval.h22.todolist.repositories;

import colval.h22.todolist.models.entities.ItemDate;
import colval.h22.todolist.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DateRepository extends JpaRepository<ItemDate, Long> {
    Optional<ItemDate> getByDate(int year, int month, int day);
}
