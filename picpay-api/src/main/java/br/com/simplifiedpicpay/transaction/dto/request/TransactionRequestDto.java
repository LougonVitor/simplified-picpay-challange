package br.com.simplifiedpicpay.transaction.dto.request;

import br.com.simplifiedpicpay.user.domain.model.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionRequestDto {
    private BigDecimal amount;
    private User sender;
    private User receiver;
    private LocalDateTime timestamp;
}