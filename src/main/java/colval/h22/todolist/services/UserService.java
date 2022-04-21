package colval.h22.todolist.services;

import colval.h22.todolist.models.InterfaceUserService;
import colval.h22.todolist.models.Item;
import colval.h22.todolist.models.User;
import colval.h22.todolist.repositories.UserRepository;
import org.springframework.stereotype.Service;

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

//    @Override
//    public User addItemsToUser(Long id, List<Item> items) {
//        var userFound = read(id);
//        var itemsOnUser = userFound.getItems();
//
//        itemsOnUser.addAll(items);
//
//        userFound.setItems(itemsOnUser);
//
//        update(userFound);
//
//        return userFound;
//    }
}
