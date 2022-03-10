package com.epam.esm.util.service.impl;

import com.epam.esm.database.dao.OrderDao;
import com.epam.esm.database.dao.UserDao;
import com.epam.esm.domain.model.entity.Order;
import com.epam.esm.domain.model.entity.Role;
import com.epam.esm.domain.model.entity.User;
import com.epam.esm.domain.model.entity.dto.UserDto;
import com.epam.esm.domain.model.entity.dto.UserWithOrderListDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.mapper.UserDtoMapper;
import com.epam.esm.util.service.UserService;
import com.epam.esm.util.validator.CustomValidator;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final String ROLE_USER = "ROLE_USER";
    private static final int USER_ID = 2;

    private UserDao userDao;
    private OrderDao orderDao;


    public UserServiceImpl(UserDao userDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with name: " + username + "isn't presence in database");
        }
        return new MyUserDetails(
                user.getUserName(),
                user.getPassword(),
                user.getActive(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }

    @Override
    public UserDetails loadUserByUsernameDelegate(String username) throws UsernameNotFoundException {
        return loadUserByUsername(username);
    }



    @Override
    public List<UserWithOrderListDto> getAllUsers(int page) throws CustomServiceException {
        CustomValidator.isNegative(page);
        Optional<BigInteger> countOptional = userDao.getRawCount();
        CustomValidator.isOperationSuccess(countOptional.isPresent());
        int count = countOptional.get().intValue();
        CustomValidator.isPageExists(page, count);
        List<User> users = userDao.getAllUsers(page);
        CustomValidator.isOperationSuccess(users);
        return users.stream()
                .map(UserDtoMapper::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserWithOrderListDto getUserById(int id) throws CustomServiceException {
        CustomValidator.isIdValid(id);
        User user = userDao.getUserById(id);
        CustomValidator.isEntityPresence(user, id);
        return UserDtoMapper.mapUserToUserDto(user);
    }

    @Override
    public List<UserWithOrderListDto> getAllUsersWithOrders(int page) throws CustomServiceException {
        List<Order> orders = orderDao.getAllOrders(page);
        CustomValidator.isOperationSuccess(orders);
        return UserDtoMapper.mapOrderListToUserDtoListWithOrderList(orders);
    }

    @Override
    public UserWithOrderListDto getUserWithOrderById(int id) throws CustomServiceException {
        CustomValidator.isIdValid(id);
        List<Order> orders = orderDao.getOrderByUserId(id);
        CustomValidator.isOperationSuccess(orders);
        return UserDtoMapper.mapOrderToUserDtoWithOrderList(orders);
    }


    @Override
    public UserDto addNewUser(UserDto userDto) throws CustomServiceException {
        User user = UserDtoMapper.mapUserDtoToUser(userDto);
        User checkUser = userDao.findUserByName(user.getUserName());
        CustomValidator.isUserPresence(checkUser);
        Role role = new Role();
        role.setId(USER_ID);
        role.setName(ROLE_USER);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(role);
        User addedUser = userDao.addNewUser(user);
        CustomValidator.isOperationSuccess(addedUser);
        return UserDtoMapper.mapUserToUserDtoRegister(addedUser);
    }

}
