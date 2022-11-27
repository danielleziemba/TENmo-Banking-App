package com.github.danielleziemba.tenmo.service;

import com.github.danielleziemba.tenmo.model.Account;
import com.github.danielleziemba.tenmo.model.User;
import com.github.danielleziemba.tenmo.repository.AccountRepository;
import com.github.danielleziemba.tenmo.repository.UserRepository;
import com.github.danielleziemba.tenmo.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    IAuthenticationFacade authenticationFacade;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public BigDecimal getAccountBalance() {
        String username = authenticationFacade.getAuthentication().getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Must be logged in to access your balance."));

        Account currentAccount = accountRepository.findById(currentUser.getId())
                .orElseThrow(() -> new RuntimeException("Must be logged in to access your balance."));
        return currentAccount.getBalance();
    }
}
