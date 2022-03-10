package com.epam.esm.database.dao;

public class SqlQueries {
    public static final String FROM_GIFT = "from Gift";
    public static final String UPDATE_GIFT_PRICE = "update Gift set price=:giftPrice where id=:giftId";
    public static final String DELETE_GIFT_BY_ID = "delete from Gift where id=:giftId";
    public static final String GET_GIFT_ID_BY_SEVERAL_TAGS = "select g_id from gift_certificate gc \n" +
            "join certificate_tag ct on gc.g_id = ct.certificate_id\n" +
            "join tag t on ct.tag_id = t.t_id \n" +
            "where t.t_name  like (:tagOneName)  or t.t_name like (:tagTwoName) group by gc.g_id having   count (g_id) = 2;";

    public static final String GET_RAW_COUNT_GIFT = "select count(*) from gift_certificate;";
    public static final String GET_ORDERS = "from Order order by user_id, c_id asc";
    public static final String GET_ORDERS_BY_USER_ID = "from Order where user_id=:userId";

    public static final String GET_WIDELY_USED_TAG = "select  tag_id from contract where user_id =:userId group by tag_id having\n" +
            "(count(tag_id) = (select max (total) from (select count(tag_id) as total from contract where user_id =:userId group by tag_id)a));";

    public static final String GET_USER_ID_HIGHEST_COST = "select user_id from contract group by user_id " +
            "having (sum (c_price) = \n" +
            "(select max (total) from (select sum (c_price) as total from contract group by user_id)a));";

    public static final String GET_ALL_TAGS = "from CustomTag";
    public static final String DELETE_TAG_BY_ID = "delete from CustomTag where id=:tagId";

    public static final String GET_RAW_COUNT_TAG = "select count(*) from tag;";

    public static final String GET_USERS = "from User";

    public static final String GET_RAW_COUNT_USER = "select count(*) from users;";

}
