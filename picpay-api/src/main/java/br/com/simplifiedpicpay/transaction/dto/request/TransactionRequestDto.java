package br.com.simplifiedpicpay.transaction.dto.request;

import br.com.simplifiedpicpay.user.domain.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequestDto (BigDecimal amount, Long senderId, Long receiverId, LocalDateTime timestamp){
}