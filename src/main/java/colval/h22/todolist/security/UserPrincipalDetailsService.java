package colval.h22.todolist.security;

import colval.h22.todolist.models.entities.User;
import colval.h22.todolist.repositories.UserRepository;
import colval.h22.todolist.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private final UserService userService;

    public UserPrincipalDetailsService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws
            UsernameNotFoundException {
        final User user =
                this.userService.findByUsername(s).orElseThrow(() -> new
                        UsernameNotFoundException("User login not found"));
        return new UserPrincipal(user);
    }
}
