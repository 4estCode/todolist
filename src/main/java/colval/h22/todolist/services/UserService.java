package colval.h22.todolist.services;

import colval.h22.todolist.models.*;
import colval.h22.todolist.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UserService implements InterfaceUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User read(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        var userFound = read(id);
        userRepository.delete(userFound);
        return userFound;
    }

    @Override
    public User addItemsToUser(Long id, List<Item> items) {
        var userFound = read(id);
        var itemsOnUser = userFound.getWeeks();

        itemsOnUser.addAll(items);

        userFound.setItems(itemsOnUser);

        update(userFound);

        return userFound;
    }

    @Override
    public Week getCurrentWeek(Long id) {
        var user = userRepository.getById(id);

        long todayMillis = System.currentTimeMillis();
        int numberOfToday = new Date(todayMillis).toLocalDate().getDayOfMonth();

        List<Week> weeks = user.getWeeks();
        // TODO FIXME
        for (var week: weeks) {

        }

        return null;
    }


}
