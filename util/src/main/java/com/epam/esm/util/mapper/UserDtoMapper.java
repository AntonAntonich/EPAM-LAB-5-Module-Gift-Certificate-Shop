package com.epam.esm.util.mapper;

import com.epam.esm.domain.model.entity.Order;
import com.epam.esm.domain.model.entity.User;
import com.epam.esm.domain.model.entity.dto.OrderDto;
import com.epam.esm.domain.model.entity.dto.UserDto;
import com.epam.esm.domain.model.entity.dto.UserWithOrderListDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDtoMapper {
    public static UserWithOrderListDto mapUserToUserDto(User user) {
        return new UserWithOrderListDto(user.getId(), user.getEmail());
    }

    public static UserWithOrderListDto mapOrderToUserDtoWithOrderList(List<Order> orders) {
        UserWithOrderListDto userWithOrderListDto = mapUserToUserDto(orders.stream()
                .findFirst().get().getUser());
        List<OrderDto> orderDtoList =
                orders.stream()
                        .map(OrderDtoMapper::mapOrderToOrderDto)
                        .peek(e -> e.setUser(null))
                        .collect(Collectors.toList());
        userWithOrderListDto.setOrders(orderDtoList);
        return userWithOrderListDto;
    }

    public static List<UserWithOrderListDto> mapOrderListToUserDtoListWithOrderList(List<Order> orders) {

        List<UserWithOrderListDto> userWithOrderListDtoList = new ArrayList<>();
        int userId = 0;
        UserWithOrderListDto userWithOrderListDto = null;
        OrderDto orderDto;

        for (Order current : orders) {
            if (userId != current.getUser().getId()) {
                userId = current.getUser().getId();
                userWithOrderListDto = UserDtoMapper.mapUserToUserDto(orders.get(orders.indexOf(current)).getUser());
                userWithOrderListDtoList.add(userWithOrderListDto);
            }
            orderDto = OrderDtoMapper.mapOrderToOrderDto(current);
            orderDto.setUser(null);
            assert userWithOrderListDto != null;
            userWithOrderListDto.addOrderDto(orderDto);
        }

        return userWithOrderListDtoList;
    }


    public static User mapUserDtoToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getUserName(),
                userDto.getPassword(),
                userDto.getActive(),
                userDto.getRole()
                );
    }

    public static UserDto mapUserToUserDtoRegister(User user) {
        return new UserDto(
          user.getId(),
          user.getEmail(),
          user.getUserName(),
          null,
          user.getActive(),
          user.getRole()
        );
    }

}
