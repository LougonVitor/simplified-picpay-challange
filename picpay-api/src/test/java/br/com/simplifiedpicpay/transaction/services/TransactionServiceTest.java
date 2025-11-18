package br.com.simplifiedpicpay.transaction.services;

import br.com.simplifiedpicpay.notification.services.NotificationService;
import br.com.simplifiedpicpay.transaction.application.ports.IAuthorizationPort;
import br.com.simplifiedpicpay.transaction.dto.request.TransactionRequestDto;
import br.com.simplifiedpicpay.transaction.repositories.TransactionRepository;
import br.com.simplifiedpicpay.user.domain.model.User;
import br.com.simplifiedpicpay.user.domain.model.UserType;
import br.com.simplifiedpicpay.user.exception.UserNotFoundException;
import br.com.simplifiedpicpay.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private TransactionRepository repository;

    @Mock
    private NotificationService notificationService;

    @Mock
    private IAuthorizationPort authorizationPort;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create transaction successfully when everything is ok")
    void shouldCreateTransactionSuccessfully() throws Exception {
        User sender = new User(
                1L
                , "Vítor"
                , "Lougon"
                , "17036492332"
                , "vitor.lougon@gmail.com"
                , "V!tor"
                , new BigDecimal(100)
                , UserType.COMMON
        );

        User receiver = new User(
                2L
                , "Diogo"
                , "Lougon"
                , "17456491332"
                , "diogo.lougon@gmail.com"
                , "D!ogo"
                , new BigDecimal(100)
                , UserType.COMMON
        );

        when(this.userService.findUserById(1L)).thenReturn(sender);
        when(this.userService.findUserById(2L)).thenReturn(receiver);

        when(this.authorizationPort.isAuthorized(any(), any())).thenReturn(true);

        TransactionRequestDto request = new TransactionRequestDto(
                new BigDecimal(10)
                , 1L
                , 2L
                , LocalDateTime.now()
        );
        this.transactionService.createTransaction(request);

        verify(this.repository, times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        verify(this.userService, times(1)).saveUser(sender);
        receiver.setBalance(new BigDecimal(20));
        verify(this.userService, times(1)).saveUser(receiver);

        verify(this.notificationService, times(1)).sendNotification(sender.getEmail(), "Transaction realized");
        verify(this.notificationService, times(1)).sendNotification(receiver.getEmail(), "Transaction realized");
    }

    @Test
    @DisplayName("Should throw exception when transaction is not allowed")
    void shouldThrowExceptionWhenTransactionIsNotAllowed() {
        User sender = new User(
                1L
                , "Vítor"
                , "Lougon"
                , "17036492332"
                , "vitor.lougon@gmail.com"
                , "V!tor"
                , new BigDecimal(100)
                , UserType.COMMON
        );

        User receiver = new User(
                2L
                , "Diogo"
                , "Lougon"
                , "17456491332"
                , "diogo.lougon@gmail.com"
                , "D!ogo"
                , new BigDecimal(100)
                , UserType.COMMON
        );

        when(this.userService.findUserById(1L)).thenReturn(sender);
        when(this.userService.findUserById(2L)).thenReturn(receiver);

        when(this.authorizationPort.isAuthorized(any(), any())).thenReturn(false);

        Exception throwsException = Assertions.assertThrows(Exception.class,() -> {
            TransactionRequestDto request = new TransactionRequestDto(
                    new BigDecimal(10)
                    , 1L
                    , 2L
                    , LocalDateTime.now()
            );
            this.transactionService.createTransaction(request);
        });

        Assertions.assertEquals("Transaction not authorized", throwsException.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when user not found")
    void shouldThrowExceptionWhenUserNotFound() {
        User sender = new User(
                1L
                , "Vítor"
                , "Lougon"
                , "17036492332"
                , "vitor.lougon@gmail.com"
                , "V!tor"
                , new BigDecimal(100)
                , UserType.COMMON
        );

        when(this.userService.findUserById(1L)).thenReturn(sender);
        when(this.userService.findUserById(2L)).thenThrow(new UserNotFoundException("User not found by id."));

        Exception throwsException = Assertions.assertThrows(UserNotFoundException.class, () -> {
            TransactionRequestDto request = new TransactionRequestDto(
                    new BigDecimal(10)
                    , 1L
                    , 2L
                    , LocalDateTime.now()
            );
            this.transactionService.createTransaction(request);
        });

        Assertions.assertEquals("User not found by id.", throwsException.getMessage());
    }
}