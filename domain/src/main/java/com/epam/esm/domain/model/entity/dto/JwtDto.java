package com.epam.esm.domain.model.entity.dto;

public class JwtDto {
    private String jwt;

    public JwtDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
