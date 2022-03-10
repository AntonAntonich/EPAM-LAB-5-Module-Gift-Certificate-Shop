package com.epam.esm.util.service;

import com.epam.esm.domain.model.entity.dto.CustomTagWithoutListDto;
import com.epam.esm.domain.model.entity.dto.OrderDto;
import com.epam.esm.util.exception.CustomServiceException;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService {
    /**
     * Create new order order dto.
     *
     * @param idGift the id gift
     * @param idTag  the id tag
     * @param idUser the id user
     * @return the order dto
     * @throws CustomServiceException the custom service exception
     */
    OrderDto createNewOrder(int idGift, int idTag, int idUser) throws CustomServiceException;

    /**
     * Gets order by user id.
     *
     * @param id the id
     * @return the order by user id
     * @throws CustomServiceException the custom service exception
     */
    List<OrderDto> getOrderByUserId(int id) throws CustomServiceException;

    /**
     * Gets order cost time by user id.
     *
     * @param id the id
     * @return the order cost time by user id
     * @throws CustomServiceException the custom service exception
     */
    List<OrderDto> getOrderCostTimeByUserId(int id) throws CustomServiceException;

    /**
     * Gets widely used tag with highest order cost.
     *
     * @return the widely used tag with highest order cost
     * @throws CustomServiceException the custom service exception
     */
    CustomTagWithoutListDto getWidelyUsedTagWithHighestOrderCost() throws CustomServiceException;
}
