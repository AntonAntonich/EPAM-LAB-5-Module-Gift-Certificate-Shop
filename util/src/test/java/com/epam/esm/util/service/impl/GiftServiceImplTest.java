package com.epam.esm.util.service.impl;

import com.epam.esm.database.dao.GiftDao;
import com.epam.esm.domain.model.entity.CustomTag;
import com.epam.esm.domain.model.entity.Gift;
import com.epam.esm.domain.model.entity.dto.GiftDto;
import com.epam.esm.domain.model.entity.dto.GiftWithoutListDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.mapper.GiftDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.hateoas.Link;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class GiftServiceImplTest {

    private Gift gift;
    private Gift gift1;

    private CustomTag customTag;
    private CustomTag customTag1;

    private List<Gift> giftList;
    private List<GiftDto> giftDtoList;
    private List<CustomTag> customTagList;

    @Mock
    private GiftDao giftDao = Mockito.mock(GiftDao.class);


    private GiftServiceImpl giftService;

    @BeforeEach
    void setUp() {
        giftService = new GiftServiceImpl(giftDao);
        customTag = new CustomTag(1, "name 1", null);
        customTag1 = new CustomTag(2, "name 2", null);

        customTagList = Arrays.asList(customTag, customTag1);

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
        giftDtoList = GiftDtoMapper.mapGiftToDto(giftList);

    }


    /**
    *
    * Checking jenkins
    *
    * */

//    @Test
//    void getAllGiftsShouldFail() throws CustomServiceException {
//        Optional<BigInteger> rawOptional = Optional.of(BigInteger.valueOf(100L));
//        when(giftDao.getAllGifts(1)).thenReturn(giftList);
//        when(giftDao.getRawCount()).thenReturn(rawOptional);
//        assertEquals(null, GiftDtoMapper.mapGiftToDto(giftDao.getAllGifts(1)));
//    }


    @Test
    void getAllGifts() throws CustomServiceException {
        Optional<BigInteger> rawOptional = Optional.of(BigInteger.valueOf(100L));
        when(giftDao.getAllGifts(1)).thenReturn(giftList);
        when(giftDao.getRawCount()).thenReturn(rawOptional);
        assertEquals(giftService.getAllGifts(1), GiftDtoMapper.mapGiftToDto(giftDao.getAllGifts(1)));
    }

    @Test
    void getGiftById() throws CustomServiceException {
        when(giftDao.getGiftById(1)).thenReturn(gift);
        GiftDto expected = GiftDtoMapper.mapGiftToDto(Collections.singletonList(giftDao.getGiftById(1))).get(0);
        expected.add(Link.of("plug"));
        GiftDto actual = giftService.getGiftById(1);
        actual.add(Link.of("plug"));
        assertEquals(actual, expected);
    }

    @Test
    void addNewGift() throws CustomServiceException {
        when(giftDao.addNewGift(gift)).thenReturn(gift1);
        GiftWithoutListDto expected = GiftDtoMapper.mapGiftToGiftWithoutListDto(giftDao.addNewGift(gift));
        GiftWithoutListDto actual = giftService.addNewGift(GiftDtoMapper.mapGiftToGiftWithoutListDto(gift));
        assertEquals(actual, expected);
    }

    @Test
    void updateGift() throws CustomServiceException {
        when(giftDao.updateGift(gift)).thenReturn(gift1);
        GiftWithoutListDto expected = GiftDtoMapper.mapGiftToGiftWithoutListDto(gift);
        GiftWithoutListDto actual = giftService.updateGift(GiftDtoMapper.mapGiftToGiftWithoutListDto(gift));
        assertEquals(actual, expected);
    }

    @Test
    void updateGiftPrice() throws CustomServiceException {
        when(giftDao.getGiftById(1)).thenReturn(gift);
        when(giftDao.updateGift(gift)).thenReturn(gift1);
        GiftWithoutListDto expected = GiftDtoMapper.mapGiftToGiftWithoutListDto(gift);
        GiftWithoutListDto actual = giftService.updateGiftPrice(1, "123");
        assertEquals(actual, expected);
    }

    @Test
    void updateGiftDuration() throws CustomServiceException {
        when(giftDao.getGiftById(1)).thenReturn(gift);
        when(giftDao.updateGift(gift)).thenReturn(gift1);
        GiftWithoutListDto expected = GiftDtoMapper.mapGiftToGiftWithoutListDto(gift);
        GiftWithoutListDto actual = giftService.updateGiftDuration(1, 1);
        assertEquals(actual, expected);
    }

    @Test
    void deleteGift() throws CustomServiceException {
        when(giftDao.getGiftById(1)).thenReturn(gift1);
        when(giftDao.deleteGiftById(1)).thenReturn(true);
        boolean actual = giftService.deleteGift(1);
        assertTrue(actual);
    }

    @Test
    void getGiftBySeveralTags() throws CustomServiceException {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        List<GiftWithoutListDto> giftWithoutListDtoList = Arrays.asList(
                GiftDtoMapper.mapGiftToGiftWithoutListDto(gift)
        );
        when(giftDao.getGiftIdBySeveralTags("1", "2")).thenReturn(idList);
        when(giftDao.getGiftById(1)).thenReturn(gift);
        List<Gift> giftActual = idList.stream().map(e -> giftDao.getGiftById(e)).collect(Collectors.toList());
        List<GiftWithoutListDto> giftWithoutListDtoExpected = giftActual
                .stream().map(GiftDtoMapper::mapGiftToGiftWithoutListDto).collect(Collectors.toList());
        List<GiftWithoutListDto> giftWithoutListDtoActual = giftService.getGiftBySeveralTags("1", "2");

        assertEquals(giftWithoutListDtoActual, giftWithoutListDtoExpected);


    }
}