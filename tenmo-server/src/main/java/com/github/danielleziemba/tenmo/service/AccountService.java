package com.github.danielleziemba.tenmo.service;

import com.github.danielleziemba.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    BigDecimal getAccountBalance();

    List<User> getAllUsers();
}
