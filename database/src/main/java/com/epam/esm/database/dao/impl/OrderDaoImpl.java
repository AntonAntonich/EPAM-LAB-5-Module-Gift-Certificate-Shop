package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.OrderDao;
import com.epam.esm.database.dao.QueryParameter;
import com.epam.esm.domain.model.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.database.dao.PageConf.LIMIT;
import static com.epam.esm.database.dao.SqlQueries.GET_ORDERS;
import static com.epam.esm.database.dao.SqlQueries.GET_ORDERS_BY_USER_ID;
import static com.epam.esm.database.dao.SqlQueries.GET_USER_ID_HIGHEST_COST;
import static com.epam.esm.database.dao.SqlQueries.GET_WIDELY_USED_TAG;

@Repository
public class OrderDaoImpl implements OrderDao {

    private EntityManager entityManager;

    public OrderDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Order> getAllOrders(int page) {
        int offset = (--page) * LIMIT;
        return entityManager.createQuery(GET_ORDERS)
                .setFirstResult(offset)
                .setMaxResults(LIMIT)
                .getResultList();
    }

    @Override
    @Transactional(rollbackOn = SQLException.class)
    public Order createNewOrder(Order order) {
        return entityManager.merge(order);
    }

    @Override
    public List<Order> getOrderByUserId(int id) {
        return entityManager.createQuery(GET_ORDERS_BY_USER_ID)
                .setParameter(QueryParameter.USER_ID.getParameter(), id)
                .getResultList();
    }


    @Override
    public Optional<Integer> getUserIdHighestOrderCost() {
        Optional<Integer> userID =
                Optional.ofNullable((Integer) entityManager.createNativeQuery(GET_USER_ID_HIGHEST_COST)
                .getResultList().stream()
                .findFirst().get());
        return userID;
    }

    @Override
    public Optional<Integer> getMostWidelyUsedTag(int id) {
        Query query = entityManager.createNativeQuery(GET_WIDELY_USED_TAG);
        query.setParameter(QueryParameter.USER_ID.getParameter(), id);
        Optional<Integer> tagId =
                Optional.ofNullable((Integer) query.getResultList().stream().findFirst().get());
        return tagId;
    }
}
