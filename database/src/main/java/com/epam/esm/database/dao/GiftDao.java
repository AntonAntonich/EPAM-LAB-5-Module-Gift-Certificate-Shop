package com.epam.esm.database.dao;

import com.epam.esm.domain.model.entity.Gift;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * The interface Gift dao.
 */

public interface GiftDao {
    /**
     * Gets all gifts.
     *
     * @param page the page
     * @return the all gifts
     */
    List<Gift> getAllGifts(int page);

    /**
     * Gets gift by id.
     *
     * @param id the id
     * @return the gift by id
     */
    Gift getGiftById(int id);

    /**
     * Add new gift gift.
     *
     * @param gift the gift
     * @return the gift
     */
    Gift addNewGift(Gift gift);

    /**
     * Update gift gift.
     *
     * @param gift the gift
     * @return the gift
     */
    Gift updateGift(Gift gift);

    /**
     * Update gift price boolean.
     *
     * @param id    the id
     * @param price the price
     * @return the boolean
     */
    boolean updateGiftPrice(int id, BigDecimal price);

    /**
     * Delete gift by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteGiftById(int id);

    /**
     * Gets gift id by several tags.
     *
     * @param tagOne the tag one
     * @param tagTwo the tag two
     * @return the gift id by several tags
     */
    List<Integer> getGiftIdBySeveralTags(String tagOne, String tagTwo);

    /**
     * Gets raw count.
     *
     * @return the raw count
     */
    Optional<BigInteger> getRawCount();
}
