package com.github.danielleziemba.tenmo.service;

import com.github.danielleziemba.tenmo.model.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User saveUser(User user);

}
