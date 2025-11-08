package br.com.simplifiedpicpay.user.service;

import br.com.simplifiedpicpay.user.domain.model.User;
import br.com.simplifiedpicpay.user.domain.model.UserType;
import br.com.simplifiedpicpay.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Users of the merchant type are not authorized to perform transactions");
        }else if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient balance");
        }
    }

    public User findUserById(Long id) throws Exception{
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("User not found by id."));
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }
}