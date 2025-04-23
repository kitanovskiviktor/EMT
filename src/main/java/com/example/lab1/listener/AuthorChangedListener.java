package com.example.lab1.listener;

import com.example.lab1.events.AuthorChangedEvent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorChangedListener {

    @PersistenceContext
    private EntityManager entityManager;

    @EventListener
    public void onAuthorChanged(AuthorChangedEvent event) {
        entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW authors_by_country").executeUpdate();
    }
}