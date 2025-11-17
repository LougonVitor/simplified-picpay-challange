package br.com.simplifiedpicpay.transaction.services;

import br.com.simplifiedpicpay.notification.services.NotificationService;
import br.com.simplifiedpicpay.transaction.application.ports.IAuthorizationPort;
import br.com.simplifiedpicpay.transaction.domain.model.Transaction;
import br.com.simplifiedpicpay.transaction.dto.request.TransactionRequestDto;
import br.com.simplifiedpicpay.transaction.dto.response.TransactionResponseDto;
import br.com.simplifiedpicpay.transaction.mapper.TransactionMapper;
import br.com.simplifiedpicpay.transaction.repositories.TransactionRepository;
import br.com.simplifiedpicpay.user.domain.model.User;
import br.com.simplifiedpicpay.user.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private IAuthorizationPort authorizationPort;

    public TransactionResponseDto createTransaction(TransactionRequestDto transactionDto) throws Exception{
        User sender = this.userService.findUserById(transactionDto.senderId());
        User receiver = this.userService.findUserById(transactionDto.receiverId());

        this.userService.validateTransaction(sender, transactionDto.amount());

        boolean isAuthorized = this.authorizationPort.isAuthorized(sender.getId(), transactionDto.amount());
        if(!isAuthorized) {
            throw new Exception("Transaction not authorized");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDto.amount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDto.amount()));
        receiver.setBalance(receiver.getBalance().add(transactionDto.amount()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender.getEmail(), "Transaction realized");
        this.notificationService.sendNotification(receiver.getEmail(), "Transaction realized");

        return TransactionMapper.toDto(newTransaction);
    }
}