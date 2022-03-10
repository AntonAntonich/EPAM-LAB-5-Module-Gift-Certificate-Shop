package com.epam.esm.domain.model.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderCostTomeDto extends AbstractOrderDto{
    private LocalDate localDate;
    private BigDecimal price;

    public OrderCostTomeDto() {
    }

    public OrderCostTomeDto(LocalDate localDate, BigDecimal price) {
        this.localDate = localDate;
        this.price = price;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
