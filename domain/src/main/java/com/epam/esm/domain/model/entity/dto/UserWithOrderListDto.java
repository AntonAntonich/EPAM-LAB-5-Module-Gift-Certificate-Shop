package com.epam.esm.domain.model.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserWithOrderListDto extends AbstractUserDto{
    private int id;

    private String email;

    private List<OrderDto> orders;

    public UserWithOrderListDto() {
    }

    public UserWithOrderListDto(int id, String email, List<OrderDto> orders) {
        this.id = id;
        this.email = email;
        this.orders = orders;
    }

    public UserWithOrderListDto(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public void addOrderDto(OrderDto orderDto) {
        if(orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(orderDto);
    }
}
