package br.com.simplifiedpicpay.user.dto.request;

import br.com.simplifiedpicpay.user.domain.model.UserType;

import java.math.BigDecimal;

public record UserRequestDto(String firstName, String lastName, String document , String email , String password , BigDecimal balance , UserType userType) {
}