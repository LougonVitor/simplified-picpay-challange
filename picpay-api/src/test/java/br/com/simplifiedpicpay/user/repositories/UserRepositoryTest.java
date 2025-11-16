package br.com.simplifiedpicpay.user.repositories;

import br.com.simplifiedpicpay.user.domain.model.User;
import br.com.simplifiedpicpay.user.domain.model.UserType;
import br.com.simplifiedpicpay.user.dto.request.UserRequestDto;
import br.com.simplifiedpicpay.user.mapper.UserMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    private User createUser(UserRequestDto request) {
        User newUser = UserMapper.toEntity(request);

        this.entityManager.persist(newUser);
        return newUser;
    }

    @Test
    @DisplayName("Should get User successfully from DB.")
    void findUserByDocumentOrEmailSuccess() {
        String document = "17036492732";
        String email = "vitor.lougon@gmail.com";
        UserRequestDto userRequestDto = new UserRequestDto("Vitor", "Lougon", document, email, "Vitor", new BigDecimal(100), UserType.COMMON);

        this.createUser(userRequestDto);

        Optional<User> result = this.userRepository.findUserByDocumentOrEmail(document,email);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User from DB when user not exists.")
    void findUserByDocumentOrEmailCase2() {
        String document = "17036492732";
        String email = "vitor.lougon@gmail.com";
        Optional<User> result = this.userRepository.findUserByDocumentOrEmail(document,email);

        assertThat(result.isEmpty()).isTrue();
    }
}