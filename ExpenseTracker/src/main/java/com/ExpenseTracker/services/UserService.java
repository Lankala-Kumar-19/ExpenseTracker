package com.ExpenseTracker.services;

import com.ExpenseTracker.dtos.UserRequestDTO;
import com.ExpenseTracker.dtos.UserResponseDTO;
import com.ExpenseTracker.entities.Users;
import com.ExpenseTracker.exceptions.DulpicateUsernameException;
import com.ExpenseTracker.exceptions.UserNotFoundException;
import com.ExpenseTracker.mappers.UserMapper;
import com.ExpenseTracker.repos.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    UserService(UserRepository userRepository,UserMapper userMapper){
        this.userRepository=userRepository;
        this.userMapper = userMapper;
    }

    private Users getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException("user with username: "+username+" not found"));
    }

    private Users getUserById(int id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user with id: "+id+" not found"));
    }


    public ResponseEntity<UserResponseDTO> register(UserRequestDTO dto) {
        Users user = userRepository.save(userMapper.toEntity(dto));
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userRepository.findAll(pageable).map(userMapper::toDTO));
    }

    public ResponseEntity<UserResponseDTO> updateUserById(int id,UserRequestDTO dto) {
        Users user = getUserById(id);
        user.setUsername(dto.getUsername());
        user.setMail(dto.getMail());

        return ResponseEntity.ok(userMapper.toDTO(userRepository.save(user)));

    }

    public ResponseEntity<UserResponseDTO> deleteUser(int id) {
        Users user = getUserById(id);
        userRepository.delete(user);
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    public ResponseEntity<UserResponseDTO> updateUserByUsername(String username, UserRequestDTO dto) {
        Users user = getUserByUsername(username);
        Users temp = userRepository.findByUsername(username).orElse(null);

        if(temp==null || user.getUsername().equals(temp.getUsername())){
            user.setUsername(dto.getUsername());
            user.setMail(dto.getMail());
            return ResponseEntity.ok(userMapper.toDTO(userRepository.save(user)));
        }
        else{
            throw new DulpicateUsernameException("user with this username already exist");
        }
    }

    public ResponseEntity<UserResponseDTO> deleteUserByUsername(String username) {
        Users user = getUserByUsername(username);
        userRepository.delete(user);
        return ResponseEntity.ok(userMapper.toDTO(user));
    }
}
