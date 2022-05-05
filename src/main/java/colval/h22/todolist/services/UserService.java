package colval.h22.todolist.services;

import colval.h22.todolist.models.*;
import colval.h22.todolist.models.interfaces.InterfaceUserService;
import colval.h22.todolist.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

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
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getByCredentials(String username, String password) {
        return userRepository.getByCredentials(username, password);
    }

    public Week getCurrentWeek(Long id) {
        var user = read(id);
        Week currentWeek = new Week();

        long todayMillis = System.currentTimeMillis();
//        int numberOfToday = new Date(todayMillis).toLocalDate().getDayOfMonth();

        Week.GenerateWeekFromDate(new Date(todayMillis).toLocalDate());

        return null;
    }


}
