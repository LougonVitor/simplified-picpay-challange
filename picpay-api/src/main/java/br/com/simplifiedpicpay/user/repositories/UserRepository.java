package br.com.simplifiedpicpay.user.repositories;

import br.com.simplifiedpicpay.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByDocumentOrEmail(String document, String email);
}