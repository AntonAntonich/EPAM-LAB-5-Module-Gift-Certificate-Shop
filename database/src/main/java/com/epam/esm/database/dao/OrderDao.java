package com.epam.esm.database.dao;

import com.epam.esm.domain.model.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * The interface Order dao.
 */
public interface OrderDao {
    /**
     * Gets all orders.
     *
     * @param page the page
     * @return the all orders
     */
    List<Order> getAllOrders(int page);

    /**
     * Create new order order.
     *
     * @param order the order
     * @return the order
     */
    Order createNewOrder(Order order);

    /**
     * Gets order by user id.
     *
     * @param id the id
     * @return the order by user id
     */
    List<Order> getOrderByUserId(int id);

    /**
     * Gets user id highest order cost.
     *
     * @return the user id highest order cost
     */
    Optional<Integer> getUserIdHighestOrderCost();

    /**
     * Gets most widely used tag.
     *
     * @param userID the user id
     * @return the most widely used tag
     */
    Optional<Integer> getMostWidelyUsedTag(int userID);

}
