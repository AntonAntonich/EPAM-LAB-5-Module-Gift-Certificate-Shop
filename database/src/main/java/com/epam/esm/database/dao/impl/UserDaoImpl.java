package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.UserDao;
import com.epam.esm.domain.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.database.dao.PageConf.LIMIT;
import static com.epam.esm.database.dao.SqlQueries.GET_RAW_COUNT_USER;
import static com.epam.esm.database.dao.SqlQueries.GET_USERS;

@Repository
public class UserDaoImpl implements UserDao {
    private EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers(int page) {
        int offset = (--page) * LIMIT;
        return entityManager.createQuery(GET_USERS, User.class)
                .setFirstResult(offset)
                .setMaxResults(LIMIT)
                .getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }



    @Override
    public Optional<BigInteger> getRawCount() {
        Optional<BigInteger> optionalCount = Optional.ofNullable((BigInteger) entityManager
                .createNativeQuery(GET_RAW_COUNT_USER)
                .getResultList().stream().findFirst().get());
        return optionalCount;
    }

    @Override
    @Transactional
    public User addNewUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public User findUserByName(String userName) {
        return entityManager.createQuery("from User where userName=:userName", User.class)
                .setParameter("userName", userName).getResultList().stream().findFirst().orElse(null);

    }
}
