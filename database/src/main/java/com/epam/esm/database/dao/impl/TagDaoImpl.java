package com.epam.esm.database.dao.impl;

import com.epam.esm.database.dao.QueryParameter;
import com.epam.esm.database.dao.TagDao;
import com.epam.esm.domain.model.entity.CustomTag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.database.dao.PageConf.LIMIT;
import static com.epam.esm.database.dao.SqlQueries.DELETE_TAG_BY_ID;
import static com.epam.esm.database.dao.SqlQueries.GET_ALL_TAGS;
import static com.epam.esm.database.dao.SqlQueries.GET_RAW_COUNT_TAG;

@Repository
public class TagDaoImpl implements TagDao {

    private EntityManager entityManager;

    public TagDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CustomTag> getAllTags(int page) {
        int offset = (--page) * LIMIT;
        return entityManager.createQuery(GET_ALL_TAGS)
                .setFirstResult(offset)
                .setMaxResults(LIMIT)
                .getResultList();
    }

    @Override
    public CustomTag getTagById(int id) {
        return entityManager.find(CustomTag.class, id);
    }

    @Override
    @Transactional
    public CustomTag addNewTag(CustomTag customTag) {
        return entityManager.merge(customTag);
    }

    @Override
    @Transactional
    public CustomTag updateTag(CustomTag customTag) {
        return entityManager.merge(customTag);
    }

    @Override
    @Transactional
    public boolean deleteTagById(int tagId) {
        return entityManager.createQuery(DELETE_TAG_BY_ID)
                .setParameter(QueryParameter.TAG_ID.getParameter(), tagId)
                .executeUpdate() == 1;
    }

    @Override
    public Optional<BigInteger> getRawCount() {
        Optional<BigInteger> optionalCount = Optional.ofNullable((BigInteger) entityManager
                .createNativeQuery(GET_RAW_COUNT_TAG)
                .getResultList().stream().findFirst().get());
        return optionalCount;
    }
}
