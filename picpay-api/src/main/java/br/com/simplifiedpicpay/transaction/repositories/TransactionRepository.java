package br.com.simplifiedpicpay.transaction.repositories;

import br.com.simplifiedpicpay.transaction.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}