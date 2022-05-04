package colval.h22.todolist.repositories;

import colval.h22.todolist.models.Day;
import colval.h22.todolist.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, Long> {
}
