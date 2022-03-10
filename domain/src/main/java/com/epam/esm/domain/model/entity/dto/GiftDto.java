package com.epam.esm.domain.model.entity.dto;



import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftDto extends AbstractGiftDto{
    private int id;
    private String name;
    private String description;
    private int duration;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate lastUpdateDate;

    private List<CustomTagWithoutListDto> tags;

    public GiftDto() {
    }

    public GiftDto(int id,
                   String name,
                   String description,
                   int duration,
                   BigDecimal price,
                   LocalDate startDate,
                   LocalDate lastUpdateDate,
                   List<CustomTagWithoutListDto> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tags = tags;
    }

    public void addTag(CustomTagWithoutListDto tag) {
        if(tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tag);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<CustomTagWithoutListDto> getTags() {
        return tags;
    }

    public void setTags(List<CustomTagWithoutListDto> tags) {
        this.tags = tags;
    }
}
