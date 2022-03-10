package com.epam.esm.database.dao;

public enum QueryParameter {
    TAG_ID("tagId"),
    GIFT_ID("giftId"),
    GIFT_PRICE("giftPrice"),
    USER_ID("userId"),
    TAG_ONE_NAME("tagOneName"),
    TAG_TWO_NAME("tagTwoName"),
    ORDER_ID("orderId");


    private String parameter;

    QueryParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
