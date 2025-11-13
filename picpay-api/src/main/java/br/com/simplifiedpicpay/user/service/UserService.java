package br.com.simplifiedpicpay.user.service;

import br.com.simplifiedpicpay.user.domain.model.User;
import br.com.simplifiedpicpay.user.domain.model.UserType;
import br.com.simplifiedpicpay.user.dto.request.UserRequestDto;
import br.com.simplifiedpicpay.user.dto.response.UserResponseDto;
import br.com.simplifiedpicpay.user.exception.UserAlreadyExistsException;
import br.com.simplifiedpicpay.user.exception.UserNotFoundException;
import br.com.simplifiedpicpay.user.mapper.UserMapper;
import br.com.simplifiedpicpay.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Users of the merchant type are not authorized to perform transactions");
        }else if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient balance");
        }
    }

    public User findUserById(Long id) throws UserNotFoundException {
        return this.repository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found by id."));
    }

    public UserResponseDto createUser(UserRequestDto userDto) {
        Optional<User> userDb = this.repository.findUserByDocument(userDto.document());

        if(userDb.isEmpty()) {
            User newUser = this.repository.save(UserMapper.toEntity(userDto));
            return UserMapper.toResponseDto(newUser);
        } else {
            throw new UserAlreadyExistsException("There is already a user registered for this document: " + userDto.document());
        }
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> usersDb = this.repository.findAll();
        List<UserResponseDto> response = new ArrayList<>();

        for(User entity: usersDb) {
            response.add(UserMapper.toResponseDto(entity));
        }

        return response;
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }
}