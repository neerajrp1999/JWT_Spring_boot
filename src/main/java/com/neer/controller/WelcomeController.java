package com.neer.controller;

import com.neer.entity.AuthRequest;
import com.neer.entity.User;
import com.neer.repository.UserRepository;
import com.neer.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
    @Autowired
    private UserRepository repository;
    @PostMapping("/register")
    public String registeruser(@RequestBody User user) throws Exception {
        repository.save(user);
        return "new user added";
    }
    @PostMapping("/welcome")
    public String welcome() throws Exception {
        //jwtUtil.validateToken
        return "new user added";
    }
}
