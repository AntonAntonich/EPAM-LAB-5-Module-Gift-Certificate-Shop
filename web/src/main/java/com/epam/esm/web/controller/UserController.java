package com.epam.esm.web.controller;

import com.epam.esm.domain.model.entity.dto.JwtDto;
import com.epam.esm.domain.model.entity.dto.UserDto;
import com.epam.esm.domain.model.entity.dto.UserWithOrderListDto;
import com.epam.esm.domain.model.entity.dto.UsernamePasswordDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.service.JwtService;
import com.epam.esm.util.service.UserService;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.esm.web.security.ConstantUrl.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<UserWithOrderListDto>> getAllUsers(
            @RequestParam(defaultValue = "1") int page) throws CustomServiceException {
        List<UserWithOrderListDto> userWithOrderListDtoList = userService.getAllUsers(page);
        for (UserWithOrderListDto userWithOrderListDto : userWithOrderListDtoList) {
            Link link = linkTo(UserController.class).slash(userWithOrderListDto.getId()).withSelfRel();
            userWithOrderListDto.add(link);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userWithOrderListDtoList);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<UserWithOrderListDto> getUserById(@PathVariable int id) throws CustomServiceException {
        UserWithOrderListDto userWithOrderListDto = userService.getUserById(id);
        Link link = linkTo(UserController.class).slash(userWithOrderListDto.getId()).withSelfRel();
        userWithOrderListDto.add(link);
        return ResponseEntity.status(HttpStatus.OK).body(userWithOrderListDto);
    }

    @GetMapping("/admin/user-contracts")
    public ResponseEntity<List<UserWithOrderListDto>> getAllUsersWithOrders(
            @RequestParam(defaultValue = "1") int page) throws CustomServiceException {
        List<UserWithOrderListDto> users = userService.getAllUsersWithOrders(page);
        for (UserWithOrderListDto user : users) {
            Link link = linkTo(UserController.class).slash(user.getId()).withSelfRel();
            user.add(link);
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/admin/user-contracts/{id}")
    public ResponseEntity<UserWithOrderListDto> getUserWithOrderById(@PathVariable int id) throws CustomServiceException {
        UserWithOrderListDto user = userService.getUserWithOrderById(id);
        Link link = linkTo(UserController.class).slash(user.getId()).withSelfRel();
        user.add(link);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/all/authenticate")
    public ResponseEntity<JwtDto> login(@RequestBody UsernamePasswordDto usernamePasswordDto) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(usernamePasswordDto.getUserName(), usernamePasswordDto.getPassword());
        authenticationManager.authenticate(token);
        UserDetails userDetails = userService.loadUserByUsernameDelegate(usernamePasswordDto.getUserName());
        String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok().body(new JwtDto(jwt));
    }

    @PostMapping("/all/signup")
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) throws CustomServiceException {
        UserDto addedUser = userService.addNewUser(userDto);
        Link linkLogin = Link.of(URL_CONTEXT + URL_LOGIN);
        Link linkSelf = linkTo(UserController.class).slash(addedUser.getId()).withSelfRel();
        addedUser.add(linkLogin, linkSelf);
        return ResponseEntity.ok(addedUser);
    }


}
