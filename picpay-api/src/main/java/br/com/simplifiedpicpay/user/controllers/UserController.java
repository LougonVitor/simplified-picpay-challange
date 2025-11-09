package br.com.simplifiedpicpay.user.controllers;

import br.com.simplifiedpicpay.user.domain.model.User;
import br.com.simplifiedpicpay.user.dto.request.UserRequestDto;
import br.com.simplifiedpicpay.user.dto.response.UserResponseDto;
import br.com.simplifiedpicpay.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto request) {
        UserResponseDto newUser = this.userService.createUser(request);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> response = this.userService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}