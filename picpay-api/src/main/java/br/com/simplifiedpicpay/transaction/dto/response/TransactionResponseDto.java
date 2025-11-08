package br.com.simplifiedpicpay.transaction.dto.response;

import br.com.simplifiedpicpay.user.domain.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDto (Long id, BigDecimal amount, Long senderId, Long receiverId){
}