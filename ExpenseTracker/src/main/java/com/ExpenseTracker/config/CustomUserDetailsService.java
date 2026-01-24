package com.ExpenseTracker.config;

import com.ExpenseTracker.entities.Users;
import com.ExpenseTracker.exceptions.UserNotFoundException;
import com.ExpenseTracker.repos.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException());

//        return User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .authorities(new SimpleGrantedAuthority(user.getRole().name()))
//                .build();
        return new CustomUserDetails(user);
    }
}
