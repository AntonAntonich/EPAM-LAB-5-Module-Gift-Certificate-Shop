package com.epam.esm.database.dao;

import com.epam.esm.domain.model.entity.CustomTag;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * The interface Tag dao.
 */
public interface TagDao {
    /**
     * Gets all tags.
     *
     * @param page the page
     * @return the all tags
     */
    List<CustomTag> getAllTags(int page);

    /**
     * Gets tag by id.
     *
     * @param id the id
     * @return the tag by id
     */
    CustomTag getTagById(int id);

    /**
     * Add new tag custom tag.
     *
     * @param customTag the custom tag
     * @return the custom tag
     */
    CustomTag addNewTag(CustomTag customTag);

    /**
     * Update tag custom tag.
     *
     * @param customTag the custom tag
     * @return the custom tag
     */
    CustomTag updateTag(CustomTag customTag);

    /**
     * Delete tag by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteTagById(int id);

    /**
     * Gets raw count.
     *
     * @return the raw count
     */
    Optional<BigInteger> getRawCount();
}
