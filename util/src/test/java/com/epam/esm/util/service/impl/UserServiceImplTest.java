package com.epam.esm.util.service.impl;

import com.epam.esm.database.dao.OrderDao;
import com.epam.esm.database.dao.UserDao;
import com.epam.esm.domain.model.entity.*;
import com.epam.esm.domain.model.entity.dto.OrderDto;
import com.epam.esm.domain.model.entity.dto.UserDto;
import com.epam.esm.domain.model.entity.dto.UserWithOrderListDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.mapper.UserDtoMapper;
import com.epam.esm.util.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private Order order;
    private User user;

    List<User> userList;
    List<Order> orderList;

    List<UserWithOrderListDto> userDtoList;
    List<OrderDto> orderDtoList;

    @Mock
    private UserDao userDao = Mockito.mock(UserDao.class);

    @Mock
    private OrderDao orderDao = Mockito.mock(OrderDao.class);

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userList = new ArrayList<>();
        orderList = new ArrayList<>();
        userService = new UserServiceImpl(userDao, orderDao);
        Role role = new Role(1, "ROLE_USER");
        user = new User(1, "name user 1", "asd", "asdad", true, role);
        User user1 = new User(2, "name user 2", "asdf", "asdad", true, role);
        Gift gift = new Gift();
        gift.setId(1);
        gift.setName("gift name");

        CustomTag customTag = new CustomTag(1, "tag name 1", null);

        Order order = new Order(1, BigDecimal.valueOf(100), LocalDate.of(2021, 10, 10),
                user, gift, customTag);
        Order order1 = new Order(2, BigDecimal.valueOf(321), LocalDate.of(2021, 10, 10),
                user, gift, customTag);
        userList.add(user);
        userList.add(user1);
        orderList.add(order);
        orderList.add(order1);

       // userDtoList = userList.stream().map(UserDtoMapper::mapUserToUserDto).collect(Collectors.toList());
       // orderDtoList = orderList.stream().map(OrderDtoMapper::mapOrderToOrderCostTime).collect(Collectors.toList());
    }

    @Test
    void getAllUsers() throws CustomServiceException {
        Optional<BigInteger> optionalBigInteger = Optional.of(BigInteger.valueOf(100L));
        when(userDao.getRawCount()).thenReturn(optionalBigInteger);
        when(userDao.getAllUsers(1)).thenReturn(userList);
        List<UserWithOrderListDto> expected = userList.stream()
                .map(UserDtoMapper::mapUserToUserDto)
                .collect(Collectors.toList());
        assertEquals(userService.getAllUsers(1), expected);
    }

    @Test
    void getUserById() throws CustomServiceException {
        when(userDao.getUserById(1)).thenReturn(user);
        assertEquals(userService.getUserById(1), UserDtoMapper.mapUserToUserDto(user));
    }

    @Test
    void getAllUsersWithOrders() throws CustomServiceException {
        when(orderDao.getAllOrders(1)).thenReturn(orderList);
        List<UserWithOrderListDto> actual = UserDtoMapper.mapOrderListToUserDtoListWithOrderList(orderList);
        assertEquals(userService.getAllUsersWithOrders(1), actual);
    }

    @Test
    void getUserWithOrderById() throws CustomServiceException {
        when(orderDao.getOrderByUserId(1)).thenReturn(orderList);
        assertEquals(userService.getUserWithOrderById(1), UserDtoMapper.mapOrderToUserDtoWithOrderList(orderList));
    }

    @Test
    void addNewUser() throws CustomServiceException {
        when(userDao.findUserByName("name")).thenReturn(null);
        when(userDao.addNewUser(user)).thenReturn(user);
        UserDto entry = UserDtoMapper.mapUserToUserDtoRegister(user);
        entry.setPassword("123");
        UserDto expected = UserDtoMapper.mapUserToUserDtoRegister(user);

        assertEquals(userService.addNewUser(entry), expected);

    }
}