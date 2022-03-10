package com.epam.esm.database.dao;

import com.epam.esm.domain.model.entity.User;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao {
    /**
     * Gets all users.
     *
     * @param page the page
     * @return the all users
     */
    List<User> getAllUsers(int page);

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    User getUserById(int id);

    /**
     * Gets raw count.
     *
     * @return the raw count
     */
    Optional<BigInteger> getRawCount();

    User addNewUser(User user);

    User findUserByName(String userName);

}
