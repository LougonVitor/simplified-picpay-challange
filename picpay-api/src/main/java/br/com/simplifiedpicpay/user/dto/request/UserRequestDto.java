package br.com.simplifiedpicpay.user.dto.request;

import br.com.simplifiedpicpay.user.domain.model.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserRequestDto(
        @NotBlank(message = "The first name field cannot be null.")
        @NotNull(message = "The first name field cannot be null.")
        String firstName,
        String lastName,
        @NotBlank(message = "The document field cannot be null.")
        @NotNull(message = "The document field cannot be null.")
        String document,
        @NotBlank(message = "The email field cannot be null.")
        @NotNull(message = "The email field cannot be null.")
        String email,
        @NotBlank(message = "The password field cannot be null.")
        @NotNull(message = "The password field cannot be null.")
        String password,
        BigDecimal balance,
        UserType userType
) {}