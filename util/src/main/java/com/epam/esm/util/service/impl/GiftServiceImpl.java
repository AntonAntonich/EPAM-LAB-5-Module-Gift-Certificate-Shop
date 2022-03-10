package com.epam.esm.util.service.impl;

import com.epam.esm.database.dao.GiftDao;
import com.epam.esm.domain.model.entity.Gift;
import com.epam.esm.domain.model.entity.dto.GiftDto;
import com.epam.esm.domain.model.entity.dto.GiftWithoutListDto;
import com.epam.esm.util.exception.CustomServiceException;
import com.epam.esm.util.mapper.GiftDtoMapper;
import com.epam.esm.util.service.GiftService;
import com.epam.esm.util.validator.CustomValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GiftServiceImpl implements GiftService {

    private GiftDao giftDao;

    public GiftServiceImpl(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    @Override
    public List<GiftDto> getAllGifts(int page) throws CustomServiceException {
        CustomValidator.isNegative(page);
        Optional<BigInteger> countOptional = giftDao.getRawCount();
        CustomValidator.isOperationSuccess(countOptional.isPresent());
        int count = countOptional.get().intValue();
        CustomValidator.isPageExists(page, count);
        List<Gift> gifts = giftDao.getAllGifts(page);
        CustomValidator.isOperationSuccess(gifts);
        return GiftDtoMapper.mapGiftToDto(gifts);
    }

    @Override
    public GiftDto getGiftById(int id) throws CustomServiceException {
        CustomValidator.isIdValid(id);
        Gift gift = giftDao.getGiftById(id);
        CustomValidator.isEntityPresence(gift, id);
        return GiftDtoMapper.mapGiftToDto(Collections.singletonList(gift)).get(0);
    }

    @Override
    public GiftWithoutListDto addNewGift(GiftWithoutListDto gift) throws CustomServiceException {
        CustomValidator.isOperationSuccess(gift);
        Gift newGift = giftDao.addNewGift(GiftDtoMapper.mapGiftWithoutListDtoToGift(gift));
        CustomValidator.isOperationSuccess(newGift);
        return GiftDtoMapper.mapGiftToGiftWithoutListDto(newGift);
    }

    @Override
    public GiftWithoutListDto updateGift(GiftWithoutListDto gift) throws CustomServiceException {
        CustomValidator.isOperationSuccess(gift);
        Gift newGift = giftDao.updateGift(GiftDtoMapper.mapGiftWithoutListDtoToGift(gift));
        CustomValidator.isOperationSuccess(newGift);
        return GiftDtoMapper.mapGiftToGiftWithoutListDto(newGift);
    }

    @Override
    @Transactional
    public GiftWithoutListDto updateGiftPrice(int id, String price) throws CustomServiceException {
        CustomValidator.isIdValid(id);
        CustomValidator.isOperationSuccess(price);
        CustomValidator.isNumber(price);
        Gift gift = giftDao.getGiftById(id);
        CustomValidator.isOperationSuccess(gift);
        BigDecimal newPrice = new BigDecimal(price);
        gift.setPrice(newPrice);
        Gift newGift = giftDao.updateGift(gift);
        CustomValidator.isOperationSuccess(newGift);
        return GiftDtoMapper.mapGiftToGiftWithoutListDto(newGift);
    }

    @Override
    public GiftWithoutListDto updateGiftDuration(int id, int duration) throws CustomServiceException {
        CustomValidator.isIdValid(id);
        CustomValidator.isIdValid(duration);
        Gift gift = giftDao.getGiftById(id);
        CustomValidator.isOperationSuccess(gift);
        gift.setDuration(duration);
        Gift newGift = giftDao.updateGift(gift);
        CustomValidator.isOperationSuccess(newGift);
        return GiftDtoMapper.mapGiftToGiftWithoutListDto(newGift);
    }

    @Override
    public boolean deleteGift(int id) throws CustomServiceException {
        CustomValidator.isIdValid(id);
        Gift gift = giftDao.getGiftById(id);
        CustomValidator.isEntityPresence(gift, id);
        return giftDao.deleteGiftById(id);
    }

    @Override
    public List<GiftWithoutListDto> getGiftBySeveralTags(String tagOne, String tagTwo) throws CustomServiceException {
        CustomValidator.isDataValid(tagOne, tagTwo);
        List<Integer> giftIdList = giftDao.getGiftIdBySeveralTags(tagOne, tagTwo);
        for (Integer integer : giftIdList) {
            CustomValidator.isIdValid(integer);
        }
        List<Gift> gifts = giftIdList.stream().map(e -> giftDao.getGiftById(e)).collect(Collectors.toList());
        CustomValidator.isOperationSuccess(gifts);
        return gifts.stream().map(GiftDtoMapper::mapGiftToGiftWithoutListDto).collect(Collectors.toList());
    }
}
