package com.epam.esm.web.controller;

import com.epam.esm.domain.model.entity.dto.JwtDto;
import com.epam.esm.domain.model.entity.dto.UserDto;
import com.epam.esm.domain.model.entity.dto.UsernamePasswordDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.service.JwtService;
import com.epam.esm.util.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")

public class TestController {

    private UserService userService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public TestController(UserService userService,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    //    @GetMapping
//    private String[] get(@RequestBody String [] array) {
//        System.out.println(Arrays.toString(array));
//        return array;
//    }

    @GetMapping
    public String homePage() {
        return ("<h1>Home page</h1>");
    }

    @GetMapping("/user")
    public String homeUser() {
        return ("<h1>Hello USER</h1>");
    }

    @GetMapping("/admin")
    public String homeAdmin() {
        return ("<h1>Home ADMIN</h1>");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtDto> login(@RequestBody UsernamePasswordDto usernamePasswordDto) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(usernamePasswordDto.getUserName(), usernamePasswordDto.getPassword());
        authenticationManager.authenticate(token);
        UserDetails userDetails = userService.loadUserByUsernameDelegate(usernamePasswordDto.getUserName());
        String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok().body(new JwtDto(jwt));
    }

    @PostMapping("/signup")
    public String addNewUser(@RequestBody UserDto userDto) throws CustomServiceException {
        userService.addNewUser(userDto);
        return "success";
    }



}
