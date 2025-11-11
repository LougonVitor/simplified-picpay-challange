package br.com.simplifiedpicpay.transaction.controller;

import br.com.simplifiedpicpay.transaction.dto.request.TransactionRequestDto;
import br.com.simplifiedpicpay.transaction.dto.response.TransactionResponseDto;
import br.com.simplifiedpicpay.transaction.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDto> createTransaction (@RequestBody @Valid TransactionRequestDto request) throws Exception{
        TransactionResponseDto response = this.transactionService.createTransaction(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}