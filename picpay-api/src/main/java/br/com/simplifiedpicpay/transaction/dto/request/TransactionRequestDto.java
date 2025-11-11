package br.com.simplifiedpicpay.transaction.dto.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequestDto (
        @NotNull(message = "Amount cannot be null.")
        BigDecimal amount,
        @NotNull(message = "Sender cannot be null.")
        Long senderId,
        @NotNull(message = "Receiver cannot be null.")
        Long receiverId,
        LocalDateTime timestamp)
{}