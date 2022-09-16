package com.cjvision.security_cj.controller;

import com.cjvision.security_cj.Entity.AuthRequest;
import com.cjvision.security_cj.Entity.AuthResponse;
import com.cjvision.security_cj.Entity.User;
import com.cjvision.security_cj.repository.UserRepository;
import com.cjvision.security_cj.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthApi {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public Object register(@RequestBody AuthRequest authRequest){

        String password = passwordEncoder.encode(authRequest.getPassword());

        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(password);
        return userRepository.save(user);
    }

    @PostMapping("/auth/login")
    public Object login(@RequestBody @Valid AuthRequest authRequest){
        System.out.println("Request payload " + authRequest);
       try {
           System.out.println("Logged from try block");
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword(), null)
           );

//           Authentication authentication = authenticationManager.authenticate(request);
//           System.out.println("Auth principal " + authentication.getPrincipal());

           User user = (User) authentication.getPrincipal();
          //  String accessToken = "JWT access token";
           String accessToken = jwtUtil.generateAccessToken(user);
           System.out.println("User token " + accessToken);
           System.out.println("User auth " + authentication);
           AuthResponse authResponse = new AuthResponse(user.getUsername(), accessToken);
           System.out.println("Response payload " + authResponse);
           return authResponse;
       }catch(BadCredentialsException exception){
           System.out.println("Bad credentials " + exception.getMessage());
            return exception.getMessage();
       }

    }
}
