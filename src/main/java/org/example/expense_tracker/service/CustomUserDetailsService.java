package org.example.expense_tracker.service;

import org.example.expense_tracker.model.User;
import org.example.expense_tracker.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

//        System.out.println("Loaded user: " + username);
//        System.out.println("Roles: ");
//        user.getRoles().forEach(role -> {
//            System.out.println(" - " + role.getName());
//        });

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role -> {
                            if (role.getName() == null) {
                                System.out.println("WARNING: Role has null name");
                            }
                            return new SimpleGrantedAuthority(role.getName());
                        })
                        .collect(Collectors.toList())
        );
    }

}
