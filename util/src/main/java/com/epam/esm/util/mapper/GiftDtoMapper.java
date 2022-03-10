package com.epam.esm.util.mapper;

import com.epam.esm.domain.model.entity.CustomTag;
import com.epam.esm.domain.model.entity.Gift;
import com.epam.esm.domain.model.entity.dto.CustomTagWithoutListDto;
import com.epam.esm.domain.model.entity.dto.GiftDto;
import com.epam.esm.domain.model.entity.dto.GiftWithoutListDto;

import java.util.ArrayList;
import java.util.List;

public class GiftDtoMapper {
    public static List<GiftDto> mapGiftToDto(List<Gift> gifts) {
        List<GiftDto> giftDtoList = new ArrayList<>();
        List<CustomTag> customTagList;

        for (Gift gift : gifts) {
            GiftDto giftDTO = new GiftDto();
            giftDTO.setId(gift.getId());
            giftDTO.setName(gift.getName());
            giftDTO.setPrice(gift.getPrice());
            giftDTO.setDuration(gift.getDuration());
            giftDTO.setDescription(gift.getDescription());
            giftDTO.setStartDate(gift.getStartDate());
            giftDTO.setLastUpdateDate(gift.getLastUpdateDate());

            customTagList = gift.getCustomTags();
            for (CustomTag tag : customTagList) {
                CustomTagWithoutListDto tagDTO = TagDtoMapper.mapTagToTagWithoutListDto(tag);
                giftDTO.addTag(tagDTO);
            }
            giftDtoList.add(giftDTO);
        }
        return giftDtoList;
    }

    public static GiftWithoutListDto mapGiftToGiftWithoutListDto(Gift gift) {
        GiftWithoutListDto giftWithoutListDto = new GiftWithoutListDto();
        giftWithoutListDto.setId(gift.getId());
        giftWithoutListDto.setName(gift.getName());
        giftWithoutListDto.setDescription(gift.getDescription());
        giftWithoutListDto.setDuration(gift.getDuration());
        giftWithoutListDto.setPrice(gift.getPrice());
        giftWithoutListDto.setStartDate(gift.getStartDate());
        giftWithoutListDto.setLastUpdateDate(gift.getLastUpdateDate());
        return giftWithoutListDto;
    }

    public static Gift mapGiftWithoutListDtoToGift(GiftWithoutListDto giftWithoutListDto) {

        return new Gift(
                giftWithoutListDto.getId(),
                giftWithoutListDto.getName(),
                giftWithoutListDto.getDescription(),
                giftWithoutListDto.getDuration(),
                giftWithoutListDto.getPrice(),
                giftWithoutListDto.getStartDate(),
                giftWithoutListDto.getLastUpdateDate(),
                null
        );
    }
}
