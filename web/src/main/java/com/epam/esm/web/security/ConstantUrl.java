package com.epam.esm.web.security;

public class ConstantUrl {
    /*
    * Roles
    * */
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    /*
     * Context
     * */
    public static final String URL_CONTEXT = "/gifts-shop";
    /*
     * URL With ROLES
     * */
    public static final String URL_ADMIN_GIFT = "/api/gifts/admin/**";
    public static final String URL_ADMIN_TAG = "/api/tags/admin/**";
    public static final String URL_ADMIN_USER = "/api/users/admin/**";
    public static final String URL_ADMIN_CONTRACT = "/api/contracts/admin/**";

    public static final String URL_USER_GIFT = "/api/gifts/user/**";
    public static final String URL_USER_TAG = "/api/tags/user/**";
    public static final String URL_USER_USER = "/api/users/user/**";
    public static final String URL_USER_CONTRACT = "/api/contracts/user/**";

    public static final String URL_ALL_GIFT = "/api/gifts/all/**";
    public static final String URL_ALL_TAG = "/api/tags/all/**";
    public static final String URL_ALL_USER = "/api/users/all/**";
    public static final String URL_ALL_CONTRACT = "/api/contracts/all/**";
    /*
     * Authentication, Registration
     * */
    public static final String URL_LOGIN = "/api/users/authenticate";
    public static final String URL_SIGNUP = "/api/users/signup";
    /*
     * GIFTS
     * */
    public static final String URL_ALL_GIFTS = "/api/gifts";
    public static final String URL_GIFT_BY_ID = "/api/gifts/{id}";
    public static final String URL_SAVE_GIFT = "/api/gifts/admin";
    public static final String URL_UPD_GIFT = "/api/gifts/admin";
    public static final String URL_UPD_GIFT_PRICE = "/api/gifts/id/{id}/price/{price}";
    public static final String URL_UPD_GIFT_DURATION = "/api/gifts/id/{id}/duration/{duration}";
    public static final String URL_DEL_GIFT = "/api/gifts/{id}";
    public static final String URL_ALL_TAGS = "/api/tags";
    public static final String URL_SEARCH_GIFTS_BY_TWO_TAGS = "/api/gifts/search-by-several-tags/tagOne/{tagOne}/tagTwo/{tagTwo}";
    /*
     * Tags
     * */
    public static final String URL_SAVE_TAG = "/api/tags";
    public static final String URL_UPD_TAG = "/api/tags";
    public static final String URL_DEL_TAG = "/api/tags/{id}";
    public static final String URL_TAG_BY_ID = "/api/tags/{id}";
    /*
    * Contracts
    * */
    public static final String URL_NEW_ORDER = "/api/contracts/idGift/{idGift}/idTag/{idTag}/idUser/{idUser}";
    public static final String URL_WIDELY_USED_TAG = "/api/contracts/widely-used-tag";
    public static final String URL_CONTRACT_BY_USER_ID = "/api/contracts/{id}";
    public static final String URL_COST_TIME_BY_CONTRACT_ID= "/api/contracts/user-contracts/cost-time/{id}";
    /*
     * Users
     * */
    public static final String URL_GET_ALL_USERS = "/api/users";
    public static final String URL_USER_BY_ID = "/api/users/{id}";
    public static final String URL_USERS_WITH_ORDERS = "/api/users/user-contracts";
    public static final String URL_USER_WITH_ORDERS_BY_USER_ID = "/api/users/user-contracts/{id}";


}
