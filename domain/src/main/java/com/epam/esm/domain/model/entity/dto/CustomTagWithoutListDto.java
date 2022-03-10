package com.epam.esm.domain.model.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomTagWithoutListDto extends AbstractTagDto{
    private int id;
    private String name;

    public CustomTagWithoutListDto() {
    }

    public CustomTagWithoutListDto(String name) {

        this.name = name;
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
}
