package com.github.danielleziemba.tenmo.contoller;

import com.github.danielleziemba.tenmo.model.ERole;
import com.github.danielleziemba.tenmo.model.Role;
import com.github.danielleziemba.tenmo.model.User;
import com.github.danielleziemba.tenmo.payload.request.LoginRequest;
import com.github.danielleziemba.tenmo.payload.request.SignupRequest;
import com.github.danielleziemba.tenmo.payload.response.JwtResponse;
import com.github.danielleziemba.tenmo.payload.response.MessageResponse;
import com.github.danielleziemba.tenmo.repository.RoleRepository;
import com.github.danielleziemba.tenmo.repository.UserRepository;
import com.github.danielleziemba.tenmo.security.jwt.JwtUtils;
import com.github.danielleziemba.tenmo.security.services.UserDetailsImpl;
import com.github.danielleziemba.tenmo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        User user = new User(
                signupRequest.getUsername(),
                encoder.encode(signupRequest.getPassword()));

        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }
}
