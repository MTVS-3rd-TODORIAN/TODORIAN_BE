package com.todorian.item.repository;

import com.todorian.item.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Item item) {
        entityManager.persist(item);
    }
}
