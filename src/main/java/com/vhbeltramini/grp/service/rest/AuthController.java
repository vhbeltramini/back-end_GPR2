package com.vhbeltramini.grp.service.rest;

import com.vhbeltramini.grp.model.LoginRequest;
import com.vhbeltramini.grp.model.LoginResponse;
import com.vhbeltramini.grp.model.User;
import com.vhbeltramini.grp.repository.UserRepository;
import com.vhbeltramini.grp.service.rest.utils.JwtTokenProvider;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
public class AuthController {

    private UserRepository userRepository;

    private JwtTokenProvider jwtTokenProvider;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserRepository userRepository, JwtTokenProvider tokenProvider) {
        super();
        this.userRepository = userRepository;
        this.jwtTokenProvider = tokenProvider;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!passwordEncoder.matches(loginRequest.getPassword() , user.get().getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String tokenByPass = "UniProjetar";
//        String token = jwtTokenProvider.createToken(Long.valueOf(user.get().getId()), user.get().getRole().toString());
        return ResponseEntity.ok(new LoginResponse(tokenByPass, user.get()));
    }

}
