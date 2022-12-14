package com.github.danielleziemba.tenmo.contoller;

import com.github.danielleziemba.tenmo.model.User;
import com.github.danielleziemba.tenmo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/account/balance")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<BigDecimal> getAccountBalance() {
        return new ResponseEntity<BigDecimal>(accountService.getAccountBalance(), HttpStatus.OK);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(accountService.getAllUsers(), HttpStatus.OK);
    }

}
