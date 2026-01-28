package com.ExpenseTracker.services;

import com.ExpenseTracker.ENUMs.Role;
import com.ExpenseTracker.dtos.UserRequestDTO;
import com.ExpenseTracker.dtos.UserResponseDTO;
import com.ExpenseTracker.entities.Users;
import com.ExpenseTracker.exceptions.DulpicateUsernameException;
import com.ExpenseTracker.exceptions.UserNotFoundException;
import com.ExpenseTracker.mappers.UserMapper;
import com.ExpenseTracker.repos.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Users getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException("User with username: " + username + " not found"));
    }

    public UserResponseDTO getUserByUsernamee(String username) {
        return userMapper.toDTO(getUserByUsername(username));
    }

    private Users getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id: " + id + " not found"));
    }



    public UserResponseDTO register(UserRequestDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new DulpicateUsernameException("Duplicate username");
        }

        Users user = userMapper.toEntity(dto);
        user.setRole(Role.ADMIN);
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        return userMapper.toDTO(userRepository.save(user));
    }


    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDTO);
    }

    public UserResponseDTO updateUserById(int id, UserRequestDTO dto) {
        Users user = getUserById(id);

        if (!user.getUsername().equals(dto.getUsername())
                && userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new DulpicateUsernameException("Duplicate username");
        }

        user.setUsername(dto.getUsername());
        user.setMail(dto.getMail());

        return userMapper.toDTO(userRepository.save(user));
    }

    public UserResponseDTO updateUserByUsername(String username, UserRequestDTO dto) {
        Users user = getUserByUsername(username);

        if (!user.getUsername().equals(dto.getUsername())
                && userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new DulpicateUsernameException("Duplicate username");
        }

        user.setUsername(dto.getUsername());
        user.setMail(dto.getMail());

        return userMapper.toDTO(userRepository.save(user));
    }

    /* ================= DELETE ================= */

    public UserResponseDTO deleteUser(int id) {
        Users user = getUserById(id);
        userRepository.delete(user);
        return userMapper.toDTO(user);
    }

    public UserResponseDTO deleteUserByUsername(String username) {
        Users user = getUserByUsername(username);
        userRepository.delete(user);
        return userMapper.toDTO(user);
    }
}
