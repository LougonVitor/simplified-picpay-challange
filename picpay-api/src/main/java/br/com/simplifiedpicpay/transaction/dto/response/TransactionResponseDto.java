package br.com.simplifiedpicpay.transaction.dto.response;

import br.com.simplifiedpicpay.transaction.dto.request.TransactionRequestDto;
import lombok.Data;

@Data
public class TransactionResponseDto extends TransactionRequestDto {
    private Long id;
}