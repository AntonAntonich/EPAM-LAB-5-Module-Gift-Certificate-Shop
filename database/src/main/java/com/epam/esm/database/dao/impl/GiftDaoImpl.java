package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.GiftDao;
import com.epam.esm.database.dao.PunctuationRegex;
import com.epam.esm.database.dao.QueryParameter;
import com.epam.esm.domain.model.entity.Gift;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.database.dao.PageConf.LIMIT;
import static com.epam.esm.database.dao.SqlQueries.*;

@Repository
public class GiftDaoImpl implements GiftDao {

    private EntityManager entityManager;

    public GiftDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Gift> getAllGifts(int page) {
        int offset = (--page) * LIMIT;
        return entityManager.createQuery(FROM_GIFT, Gift.class)
                .setFirstResult(offset)
                .setMaxResults(LIMIT)
                .getResultList();
    }

    @Override
    public Gift getGiftById(int id) {
        return entityManager.find(Gift.class, id);
    }

    @Override
    @Transactional
    public Gift addNewGift(Gift gift) {
        return entityManager.merge(gift);
    }

    @Override
    @Transactional
    public Gift updateGift(Gift gift) {
        return entityManager.merge(gift);
    }

    @Override
    @Transactional
    public boolean updateGiftPrice(int id, BigDecimal price) {
        Query query = entityManager.createQuery(UPDATE_GIFT_PRICE);
        query.setParameter(QueryParameter.GIFT_ID.getParameter(), id);
        query.setParameter(QueryParameter.GIFT_PRICE.getParameter(), price);
        int i = query.executeUpdate();
        entityManager.flush();
        return i == 1;
    }

    @Override
    @Transactional
    public boolean deleteGiftById(int id) {
        return entityManager.createQuery(DELETE_GIFT_BY_ID)
                .setParameter(QueryParameter.GIFT_ID.getParameter(), id)
                .executeUpdate() == 1;
    }

    @Override
    public List<Integer> getGiftIdBySeveralTags(String tagOne, String tagTwo) {
        tagOne += PunctuationRegex.PERCENT;
        tagTwo += PunctuationRegex.PERCENT;
        return entityManager.createNativeQuery(GET_GIFT_ID_BY_SEVERAL_TAGS)
                .setParameter(QueryParameter.TAG_ONE_NAME.getParameter(), tagOne)
                .setParameter(QueryParameter.TAG_TWO_NAME.getParameter(), tagTwo)
                .getResultList();
    }


    @Override
    public Optional<BigInteger> getRawCount(){
        return Optional.ofNullable((BigInteger)entityManager
                .createNativeQuery(GET_RAW_COUNT_GIFT)
                .getResultList().stream().findFirst().get());
    }
}
