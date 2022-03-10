package com.epam.esm.util.service.impl;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.domain.model.entity.CustomTag;
import com.epam.esm.domain.model.entity.dto.CustomTagDto;
import com.epam.esm.domain.model.entity.dto.CustomTagWithoutListDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.mapper.TagDtoMapper;
import com.epam.esm.util.service.TagService;
import com.epam.esm.util.validator.CustomValidator;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private TagDao tagDao;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<CustomTagDto> getAllTags(int page) throws CustomServiceException {
        CustomValidator.isNegative(page);
        Optional<BigInteger> countOptional = tagDao.getRawCount();
        CustomValidator.isOperationSuccess(countOptional.isPresent());
        int count = countOptional.get().intValue();
        CustomValidator.isPageExists(page, count);
        List<CustomTag> tags = tagDao.getAllTags(page);
        CustomValidator.isOperationSuccess(tags);
        return TagDtoMapper.mapTagToDto(tags);
    }

    @Override
    public CustomTagDto getTagById(int id) throws CustomServiceException {
        CustomValidator.isIdValid(id);
        CustomTag customTag = tagDao.getTagById(id);
        CustomValidator.isEntityPresence(customTag, id);
        return TagDtoMapper.mapTagToDto(Collections.singletonList(customTag)).get(0);
    }

    @Override
    public CustomTagWithoutListDto addNewTag(CustomTagWithoutListDto tag) throws CustomServiceException {
        CustomValidator.isOperationSuccess(tag);
        CustomTag newTag = tagDao.addNewTag(TagDtoMapper.mapCustomTagDtoWithoutListToCustomTag(tag));
        CustomValidator.isOperationSuccess(newTag);
        return TagDtoMapper.mapTagToTagWithoutListDto(newTag);
    }

    @Override
    public CustomTagWithoutListDto updateTag(CustomTagWithoutListDto tag) throws CustomServiceException {
        CustomValidator.isOperationSuccess(tag);
        CustomTag newTag = tagDao.updateTag(TagDtoMapper.mapCustomTagDtoWithoutListToCustomTag(tag));
        CustomValidator.isOperationSuccess(newTag);
        return TagDtoMapper.mapTagToTagWithoutListDto(newTag);
    }

    @Override
    public boolean deleteTag(int id) throws CustomServiceException {
        CustomValidator.isIdValid(id);
        CustomTag customTag = tagDao.getTagById(id);
        CustomValidator.isEntityPresence(customTag, id);
        return tagDao.deleteTagById(id);
    }


}
