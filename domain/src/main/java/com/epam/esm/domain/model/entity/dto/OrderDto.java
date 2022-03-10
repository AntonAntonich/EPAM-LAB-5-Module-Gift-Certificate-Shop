package com.epam.esm.domain.model.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto extends AbstractTagDto{
    private int id;
    private BigDecimal price;
    private LocalDate date;
    private UserWithOrderListDto user;
    private GiftWithoutListDto gift;
    private CustomTagWithoutListDto tag;

    public OrderDto() {
    }

    public OrderDto(int id,
                    BigDecimal price,
                    LocalDate date,
                    UserWithOrderListDto userWithOrderListDto,
                    GiftWithoutListDto giftWithoutListDto,
                    CustomTagWithoutListDto customTagWithoutListDto) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.user = userWithOrderListDto;
        this.gift = giftWithoutListDto;
        this.tag = customTagWithoutListDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserWithOrderListDto getUser() {
        return user;
    }

    public void setUser(UserWithOrderListDto user) {
        this.user = user;
    }

    public GiftWithoutListDto getGift() {
        return gift;
    }

    public void setGift(GiftWithoutListDto gift) {
        this.gift = gift;
    }

    public CustomTagWithoutListDto getTag() {
        return tag;
    }

    public void setTag(CustomTagWithoutListDto tag) {
        this.tag = tag;
    }
}
