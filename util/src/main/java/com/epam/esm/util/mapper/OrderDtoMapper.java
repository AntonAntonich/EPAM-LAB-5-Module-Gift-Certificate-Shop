package com.epam.esm.util.mapper;

import com.epam.esm.domain.model.entity.Order;
import com.epam.esm.domain.model.entity.dto.GiftWithoutListDto;
import com.epam.esm.domain.model.entity.dto.OrderDto;

public class OrderDtoMapper {
    public static OrderDto mapOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDate(order.getDate());
        orderDto.setPrice(order.getPrice());
        orderDto.setGift(GiftDtoMapper.mapGiftToGiftWithoutListDto(order.getGift()));
        orderDto.setTag(TagDtoMapper.mapTagToTagWithoutListDto(order.getTag()));
        orderDto.setUser(UserDtoMapper.mapUserToUserDto(order.getUser()));
        return orderDto;
    }

    public static OrderDto mapOrderToOrderCostTime(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDate(order.getDate());
        orderDto.setPrice(order.getPrice());
        GiftWithoutListDto giftWithoutListDto = GiftDtoMapper.mapGiftToGiftWithoutListDto(order.getGift());
        giftWithoutListDto.setName(null);
        giftWithoutListDto.setLastUpdateDate(null);
        giftWithoutListDto.setStartDate(null);
        giftWithoutListDto.setDescription(null);
        orderDto.setGift((giftWithoutListDto));
        orderDto.setTag(null);
        orderDto.setUser(null);
        return orderDto;
    }


}
