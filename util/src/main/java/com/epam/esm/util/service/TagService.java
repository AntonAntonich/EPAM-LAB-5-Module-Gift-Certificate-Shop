package com.epam.esm.util.service;

import com.epam.esm.domain.model.entity.dto.CustomTagDto;
import com.epam.esm.domain.model.entity.dto.CustomTagWithoutListDto;
import com.epam.esm.util.exception.CustomServiceException;

import java.util.List;


/**
 * The interface Tag service.
 */
public interface TagService {

    /**
     * Gets all tags.
     *
     * @param page the page
     * @return the all tags
     * @throws CustomServiceException the custom service exception
     */
    List<CustomTagDto> getAllTags(int page) throws CustomServiceException;

    /**
     * Gets tag by id.
     *
     * @param id the id
     * @return the tag by id
     * @throws CustomServiceException the custom service exception
     */
    CustomTagDto getTagById(int id) throws CustomServiceException;

    /**
     * Add new tag custom tag without list dto.
     *
     * @param tag the tag
     * @return the custom tag without list dto
     * @throws CustomServiceException the custom service exception
     */
    CustomTagWithoutListDto addNewTag(CustomTagWithoutListDto tag) throws CustomServiceException;

    /**
     * Update tag custom tag without list dto.
     *
     * @param tag the tag
     * @return the custom tag without list dto
     * @throws CustomServiceException the custom service exception
     */
    CustomTagWithoutListDto updateTag(CustomTagWithoutListDto tag) throws CustomServiceException;

    /**
     * Delete tag boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws CustomServiceException the custom service exception
     */
    boolean deleteTag(int id) throws CustomServiceException;
}
