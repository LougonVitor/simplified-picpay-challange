package br.com.simplifiedpicpay.user.dto.response;

import java.math.BigDecimal;

public record UserResponseDto(Long id, String firstName, String lastName, String document , String email , String password , BigDecimal balance , int userType) {
}