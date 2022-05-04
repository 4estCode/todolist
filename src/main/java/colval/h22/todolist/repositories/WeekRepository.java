package colval.h22.todolist.repositories;

import colval.h22.todolist.models.Day;
import colval.h22.todolist.models.Week;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekRepository extends JpaRepository<Week, Long> {
}
