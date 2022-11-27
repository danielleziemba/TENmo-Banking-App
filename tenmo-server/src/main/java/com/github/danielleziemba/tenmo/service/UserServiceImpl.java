package com.github.danielleziemba.tenmo.service;

import com.github.danielleziemba.tenmo.model.Account;
import com.github.danielleziemba.tenmo.model.ERole;
import com.github.danielleziemba.tenmo.model.Role;
import com.github.danielleziemba.tenmo.model.User;
import com.github.danielleziemba.tenmo.repository.AccountRepository;
import com.github.danielleziemba.tenmo.repository.RoleRepository;
import com.github.danielleziemba.tenmo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public User saveUser(User user) {
        // check if username already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User already exists with username " + user.getUsername());
        }

        //set roles to user instance
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
        roles.add(userRole);
        user.setRoles(roles);

        //create account for user
        Account account = new Account();
        account.setAccountId(user.getId());
        account.setUser(user);

        accountRepository.save(account);

        return userRepository.save(user);
    }
}
