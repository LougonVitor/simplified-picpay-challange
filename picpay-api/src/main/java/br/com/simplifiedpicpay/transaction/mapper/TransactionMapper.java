package br.com.simplifiedpicpay.transaction.mapper;

import br.com.simplifiedpicpay.transaction.domain.model.Transaction;
import br.com.simplifiedpicpay.transaction.dto.request.TransactionRequestDto;
import br.com.simplifiedpicpay.transaction.dto.response.TransactionResponseDto;
import br.com.simplifiedpicpay.user.domain.model.User;
import br.com.simplifiedpicpay.user.repositories.UserRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class TransactionMapper {
    public static TransactionResponseDto toDto(Transaction request) {
        return new TransactionResponseDto(
                request.getId()
                , request.getAmount()
                , request.getSender().getId()
                , request.getReceiver().getId()
                , request.getTimestamp()
        );
    }
}