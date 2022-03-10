package com.epam.esm.util.service;

import com.epam.esm.domain.model.entity.dto.GiftDto;
import com.epam.esm.domain.model.entity.dto.GiftWithoutListDto;
import com.epam.esm.util.exception.CustomServiceException;

import java.util.List;

/**
 * The interface Gift service.
 */
public interface GiftService {
    /**
     * Gets all gifts.
     *
     * @param page the page
     * @return the all gifts
     * @throws CustomServiceException the custom service exception
     */
    List<GiftDto> getAllGifts(int page) throws CustomServiceException;

    /**
     * Gets gift by id.
     *
     * @param id the id
     * @return the gift by id
     * @throws CustomServiceException the custom service exception
     */
    GiftDto getGiftById(int id) throws CustomServiceException;

    /**
     * Add new gift gift without list dto.
     *
     * @param gift the gift
     * @return the gift without list dto
     * @throws CustomServiceException the custom service exception
     */
    GiftWithoutListDto addNewGift(GiftWithoutListDto gift) throws CustomServiceException;

    /**
     * Update gift gift without list dto.
     *
     * @param gift the gift
     * @return the gift without list dto
     * @throws CustomServiceException the custom service exception
     */
    GiftWithoutListDto updateGift(GiftWithoutListDto gift) throws CustomServiceException;

    /**
     * Update gift price gift without list dto.
     *
     * @param id    the id
     * @param price the price
     * @return the gift without list dto
     * @throws CustomServiceException the custom service exception
     */
    GiftWithoutListDto updateGiftPrice(int id, String price) throws CustomServiceException;

    /**
     * Update gift duration gift without list dto.
     *
     * @param id       the id
     * @param duration the duration
     * @return the gift without list dto
     * @throws CustomServiceException the custom service exception
     */
    GiftWithoutListDto updateGiftDuration(int id, int  duration) throws CustomServiceException;

    /**
     * Delete gift boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws CustomServiceException the custom service exception
     */
    boolean deleteGift(int id) throws CustomServiceException;

    /**
     * Gets gift by several tags.
     *
     * @param tagOne the tag one
     * @param tagTwo the tag two
     * @return the gift by several tags
     * @throws CustomServiceException the custom service exception
     */
    List<GiftWithoutListDto> getGiftBySeveralTags(String tagOne, String tagTwo) throws CustomServiceException;
}
