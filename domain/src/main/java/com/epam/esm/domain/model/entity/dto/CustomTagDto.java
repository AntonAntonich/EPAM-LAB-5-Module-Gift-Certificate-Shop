package com.epam.esm.domain.model.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomTagDto extends AbstractTagDto{
    private int id;
    private String name;
    private List<GiftWithoutListDto> gifts;

    public CustomTagDto() {
    }

    public CustomTagDto(String name, List<GiftWithoutListDto> gifts) {
        this.name = name;
        this.gifts = gifts;
    }

    public void addGift(GiftWithoutListDto gift) {
        if(gifts == null) {
            gifts = new ArrayList<>();
        }
        gifts.add(gift);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GiftWithoutListDto> getGifts() {
        return gifts;
    }

    public void setGifts(List<GiftWithoutListDto> gifts) {
        this.gifts = gifts;
    }
}
