package com.epam.esm.util.service.impl;

import com.epam.esm.database.dao.TagDao;
import com.epam.esm.domain.model.entity.CustomTag;
import com.epam.esm.domain.model.entity.Gift;
import com.epam.esm.domain.model.entity.dto.CustomTagDto;
import com.epam.esm.domain.model.entity.dto.CustomTagWithoutListDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.mapper.TagDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
//@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    private CustomTag tag;
    private CustomTag tag1;

    private Gift gift;
    private Gift gift1;

    private List<CustomTag> customTagList;
    private List<CustomTagDto> customTagDtoList;
    private List<Gift> giftList;

    @Mock
    private TagDao tagDao = Mockito.mock(TagDao.class);

    private TagServiceImpl tagService;

    @BeforeEach
    void setUp() {
        tagService = new TagServiceImpl(tagDao);
        gift = new Gift(
                1,
                "name 1",
                "description 1",
                100,
                BigDecimal.valueOf(100),
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2022, 10, 1),
                customTagList);

        gift1 = new Gift(
                2,
                "name 2",
                "description 2",
                200,
                BigDecimal.valueOf(200),
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2022, 12, 1),
                customTagList);

        giftList = Arrays.asList(gift, gift1);

        tag = new CustomTag(1, "name 1", giftList);
        tag1 = new CustomTag(2, "name 2", giftList);


        customTagList = Arrays.asList(tag, tag1);
        customTagDtoList = TagDtoMapper.mapTagToDto(customTagList);

    }

    @Test
    void getAllTagsTest() throws CustomServiceException {
        when(tagDao.getAllTags(1)).thenReturn(customTagList);
        when(tagDao.getRawCount()).thenReturn(Optional.of(BigInteger.valueOf(100)));
        List<CustomTagDto> expected = customTagDtoList;
        List<CustomTagDto> actual = tagService.getAllTags(1);
        assertEquals(actual, expected);
    }

    @Test
    void getTagById() throws CustomServiceException {
        when(tagDao.getTagById(1)).thenReturn(tag);
        CustomTagDto customTagDto = TagDtoMapper.mapTagToDto(Collections.singletonList(tag)).get(0);
        assertEquals(tagService.getTagById(1), customTagDto);
    }

    @Test
    void addNewTag() throws CustomServiceException {
        when(tagDao.addNewTag(tag)).thenReturn(tag1);
        CustomTagWithoutListDto expected = TagDtoMapper.mapTagToTagWithoutListDto(tag1);
        assertEquals(TagDtoMapper.mapTagToTagWithoutListDto(tag1), tagService.addNewTag(expected));
    }

    @Test
    void updateTag() throws CustomServiceException {
        when(tagDao.updateTag(tag)).thenReturn(tag1);
        CustomTagWithoutListDto expected = TagDtoMapper.mapTagToTagWithoutListDto(tag1);
        assertEquals(TagDtoMapper.mapTagToTagWithoutListDto(tag1), tagService.updateTag(expected));
    }

    @Test
    void deleteTag() throws CustomServiceException {
        when(tagDao.deleteTagById(1)).thenReturn(true);
        when(tagDao.getTagById(1)).thenReturn(tag);
        assertEquals(tagService.deleteTag(1), true);
    }
}