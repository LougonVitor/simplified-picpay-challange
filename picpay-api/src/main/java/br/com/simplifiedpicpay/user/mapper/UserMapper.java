package br.com.simplifiedpicpay.user.mapper;

import br.com.simplifiedpicpay.user.domain.model.User;
import br.com.simplifiedpicpay.user.domain.model.UserType;
import br.com.simplifiedpicpay.user.dto.request.UserRequestDto;
import br.com.simplifiedpicpay.user.dto.response.UserResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public class UserMapper {
    public static User toEntity(UserRequestDto request) {
        User user = new User();

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setDocument(request.document());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setBalance(request.balance());
        user.setUserType((UserType)request.userType());

        return user;
    }

    public static UserResponseDto toResponseDto(User request) {
        return new UserResponseDto(
                request.getId()
                , request.getFirstName()
                , request.getLastName()
                , request.getDocument()
                , request.getEmail()
                , request.getPassword()
                , request.getBalance()
                , request.getUserType()
        );
    }
}