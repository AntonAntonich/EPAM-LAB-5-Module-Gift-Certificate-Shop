package com.epam.esm.util.service.impl;

import com.epam.esm.database.dao.GiftDao;
import com.epam.esm.database.dao.OrderDao;
import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.dao.UserDao;
import com.epam.esm.domain.model.entity.CustomTag;
import com.epam.esm.domain.model.entity.Gift;
import com.epam.esm.domain.model.entity.Order;
import com.epam.esm.domain.model.entity.User;
import com.epam.esm.domain.model.entity.dto.CustomTagWithoutListDto;
import com.epam.esm.domain.model.entity.dto.OrderDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.mapper.OrderDtoMapper;
import com.epam.esm.util.mapper.TagDtoMapper;
import com.epam.esm.util.service.OrderService;
import com.epam.esm.util.validator.CustomValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private GiftDao giftDao;
    private TagDao tagDao;
    private UserDao userDao;

    public OrderServiceImpl(OrderDao orderDao,
                            GiftDao giftDao,
                            TagDao tagDao,
                            UserDao userDao) {
        this.orderDao = orderDao;
        this.giftDao = giftDao;
        this.tagDao = tagDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public OrderDto createNewOrder(int idGift, int idTag, int idUser) throws CustomServiceException {
        CustomValidator.isIdValid(idGift, idTag, idUser);
        Gift gift = giftDao.getGiftById(idGift);
        CustomTag tag = tagDao.getTagById(idTag);
        User user = userDao.getUserById(idUser);
        CustomValidator.isOperationSuccess(gift, tag, user);
        Order order = new Order();
        order.setDate(gift.getStartDate());
        order.setPrice(gift.getPrice());
        order.setGift(gift);
        order.setTag(tag);
        order.setUser(user);
        Order newOrder = orderDao.createNewOrder(order);
        CustomValidator.isOperationSuccess(newOrder);
        return OrderDtoMapper.mapOrderToOrderDto(newOrder);
    }

    @Override
    public List<OrderDto> getOrderByUserId(int id) throws CustomServiceException {
        CustomValidator.isIdValid(id);
        List<Order> orders = orderDao.getOrderByUserId(id);
        CustomValidator.isEntityPresence(orders, id);
        return orders.stream()
                .map(OrderDtoMapper::mapOrderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderCostTimeByUserId(int id) throws CustomServiceException {
        CustomValidator.isIdValid(id);
        List<Order> orders  = orderDao.getOrderByUserId(id);
        CustomValidator.isEntityPresence(orders, id);
        return orders.stream()
                .map(OrderDtoMapper::mapOrderToOrderCostTime)
                .collect(Collectors.toList());
    }

    @Override
    public CustomTagWithoutListDto getWidelyUsedTagWithHighestOrderCost() throws CustomServiceException {
        Optional<Integer> userId = orderDao.getUserIdHighestOrderCost();
        CustomValidator.isOperationSuccess(userId.isPresent());
        Optional<Integer> tagId = orderDao.getMostWidelyUsedTag(userId.get());
        CustomValidator.isOperationSuccess(tagId.isPresent());
        CustomTag tag = tagDao.getTagById(tagId.get());
        CustomValidator.isOperationSuccess(tag);
        return TagDtoMapper.mapTagToTagWithoutListDto(tag);
    }
}
