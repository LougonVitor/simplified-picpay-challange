package br.com.simplifiedpicpay.user.dto.response;

import br.com.simplifiedpicpay.user.domain.model.UserType;

import java.math.BigDecimal;

public record UserResponseDto(Long id, String firstName, String lastName, String document , String email , String password , BigDecimal balance , UserType userType) {
}