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

    public UserResponseDto createUser(UserRequestDto userDto) {
        Optional<User> userDb = this.repository.findUserByDocumentOrEmail(userDto.document(), userDto.email());

        if(userDb.isPresent()) {
            String conflictErrorMessage = this.buildConflictMessage(userDto, userDb.get());
            throw new UserAlreadyExistsException(conflictErrorMessage);
        }

        User newUser = this.repository.save(UserMapper.toEntity(userDto));
        return UserMapper.toResponseDto(newUser);
    }

    private String buildConflictMessage(UserRequestDto request, User existingUser) {
        boolean documentConflict = existingUser.getDocument().equals(request.document());
        boolean emailConflict = existingUser.getEmail().equals(request.email());

        if(documentConflict && emailConflict) {
            return "There is already a user registered for this document: " + request.document() + " and this email: " + request.email();
        } else if (documentConflict) {
            return "There is already a user registered for this document: " + request.document();
        } else if (emailConflict) {
            return "There is already a user registered for this email: " + request.email();
        }

        return "There is already a user registered with conflicting data.";
    }
}