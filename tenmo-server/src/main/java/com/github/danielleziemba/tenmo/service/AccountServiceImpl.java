package com.github.danielleziemba.tenmo.service;

import com.github.danielleziemba.tenmo.model.Account;
import com.github.danielleziemba.tenmo.model.User;
import com.github.danielleziemba.tenmo.repository.AccountRepository;
import com.github.danielleziemba.tenmo.repository.UserRepository;
import com.github.danielleziemba.tenmo.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    IAuthenticationFacade authenticationFacade;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    private Account currentAccount() {
        String username = authenticationFacade.getAuthentication().getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Must be logged in."));

        Account currentAccount = accountRepository.findById(currentUser.getId())
                .orElseThrow(() -> new RuntimeException("Must be logged in."));

        return currentAccount;
    }

    @Override
    public BigDecimal getAccountBalance() {
        return currentAccount().getBalance();
    }

    @Override
    public List<User> getAllUsers() {
        Long currentUserId = currentAccount().getUser().getId();
        List<User> usersForTransfer = new ArrayList<>();
        userRepository.findAll().stream()
                .filter(user -> user.getId() != currentUserId)
                .forEach(usersForTransfer::add);
        return usersForTransfer;
    }
}
