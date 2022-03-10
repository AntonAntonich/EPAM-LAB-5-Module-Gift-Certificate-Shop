package com.epam.esm.util.mapper;

import com.epam.esm.domain.model.entity.CustomTag;
import com.epam.esm.domain.model.entity.Gift;
import com.epam.esm.domain.model.entity.dto.CustomTagDto;
import com.epam.esm.domain.model.entity.dto.CustomTagWithoutListDto;
import com.epam.esm.domain.model.entity.dto.GiftWithoutListDto;

import java.util.ArrayList;
import java.util.List;

public class TagDtoMapper {

    public static List<CustomTagDto> mapTagToDto(List<CustomTag> tags) {
        List<CustomTagDto> customTagDtoList = new ArrayList<>();
        List<Gift> giftList;

        for(CustomTag tag : tags) {
            CustomTagDto customTagDto = new CustomTagDto();
            customTagDto.setId(tag.getId());
            customTagDto.setName(tag.getName());
            giftList = tag.getGifts();
            for(Gift gift : giftList) {
                GiftWithoutListDto giftWithoutListDto = GiftDtoMapper.mapGiftToGiftWithoutListDto(gift);
                customTagDto.addGift(giftWithoutListDto);
            }
            customTagDtoList.add(customTagDto);
        }
        return customTagDtoList;
    }

    public static CustomTagWithoutListDto mapTagToTagWithoutListDto(CustomTag tag) {
        CustomTagWithoutListDto tagWithoutList = new CustomTagWithoutListDto();
        tagWithoutList.setId(tag.getId());
        tagWithoutList.setName(tag.getName());
        return tagWithoutList;
    }

    public static CustomTag mapCustomTagDtoWithoutListToCustomTag(CustomTagWithoutListDto customTagWithoutListDto) {
        return new CustomTag(
          customTagWithoutListDto.getId(),
          customTagWithoutListDto.getName(),
          null
        );
    }
}
