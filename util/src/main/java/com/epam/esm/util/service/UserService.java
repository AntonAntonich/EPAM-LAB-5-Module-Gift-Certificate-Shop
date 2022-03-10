package com.epam.esm.util.service;

import com.epam.esm.domain.model.entity.dto.UserDto;
import com.epam.esm.domain.model.entity.dto.UserWithOrderListDto;
import com.epam.esm.util.exception.CustomServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Gets all users.
     *
     * @param page the page
     * @return the all users
     * @throws CustomServiceException the custom service exception
     */
    List<UserWithOrderListDto> getAllUsers(int page) throws CustomServiceException;

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     * @throws CustomServiceException the custom service exception
     */
    UserWithOrderListDto getUserById(int id) throws CustomServiceException;

    /**
     * Gets all users with orders.
     *
     * @param page the page
     * @return the all users with orders
     * @throws CustomServiceException the custom service exception
     */
    List<UserWithOrderListDto> getAllUsersWithOrders(int page) throws CustomServiceException;

    /**
     * Gets user with order by id.
     *
     * @param id the id
     * @return the user with order by id
     * @throws CustomServiceException the custom service exception
     */
    UserWithOrderListDto getUserWithOrderById(int id) throws CustomServiceException;

    /**
     * Add new user user dto.
     *
     * @param user the user
     * @return the user dto
     * @throws CustomServiceException the custom service exception
     */
    UserDto addNewUser(UserDto user) throws CustomServiceException;

    /**
     * Load user by username delegate user details.
     *
     * @param username the username
     * @return the user details
     * @throws UsernameNotFoundException the username not found exception
     */
    UserDetails loadUserByUsernameDelegate(String username) throws UsernameNotFoundException;


}
