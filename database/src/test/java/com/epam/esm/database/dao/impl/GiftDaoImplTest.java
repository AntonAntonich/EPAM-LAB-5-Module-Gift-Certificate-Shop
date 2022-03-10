package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.GiftDao;
import com.epam.esm.domain.model.entity.CustomTag;
import com.epam.esm.domain.model.entity.Gift;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GiftDaoImplTest {
    private static EmbeddedDatabase database;
    @Autowired
    private GiftDao giftDao;

    private List<Gift> gifts;
    private Gift gift;
    private Gift gift1;
    private List<CustomTag> tags;
    private CustomTag tag;
    private CustomTag tag1;

    @BeforeEach
    void setUp() {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create_db_script_2.sql")
                .build();
    }

    @Test
    void getAllGifts() {
        Gift actual = giftDao.getGiftById(1);
        Gift expected = new Gift(
                        1,
                        "gym",
                        "sport gym", 30,
                BigDecimal.valueOf(100.0),
                        LocalDate.of(2020, 12, 5),
                        LocalDate.of(2020, 12, 7),
                        Arrays.asList(
                                new CustomTag(1, "Good ivent", null)
                        ));
        assertEquals(actual, expected);
    }

    @Test
    void getGiftById() {
    }

    @Test
    void addNewGift() {
    }

    @Test
    void updateGift() {
    }

    @Test
    void updateGiftPrice() {
    }

    @Test
    void deleteGiftById() {
    }

    @Test
    void getGiftIdBySeveralTags() {
    }

    @Test
    void getRawCount() {
    }
}