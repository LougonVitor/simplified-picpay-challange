package br.com.simplifiedpicpay.transaction.mapper;

import br.com.simplifiedpicpay.transaction.domain.model.Transaction;
import br.com.simplifiedpicpay.transaction.dto.request.TransactionRequestDto;
import br.com.simplifiedpicpay.transaction.dto.response.TransactionResponseDto;
import br.com.simplifiedpicpay.user.domain.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionMapper {
    public static Transaction toEntity(TransactionRequestDto request) {
        Transaction entity = new Transaction();

        entity.setAmount(request.amount());
        entity.setSender(request.sender());
        entity.setReceiver(request.receiver());
        entity.setTimestamp(request.timestamp());

        return entity;
    }

    public static TransactionResponseDto toDto(Transaction request) {
        return new TransactionResponseDto(
                request.getId()
                , request.getAmount()
                , request.getSender()
                , request.getReceiver()
                , request.getTimestamp()
        );
    }
}