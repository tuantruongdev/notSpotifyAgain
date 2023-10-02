package com.people.connector.repositories.user;

import com.people.connector.models.User;
import com.people.connector.repositories.user.CustomUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> findUsersWithName(String name) {

        TypedQuery<User> query =entityManager.createQuery("""
                select u from User u where u.realName = :name
                """,User.class).setParameter("name",name);
        return query.getResultList();
    }
}
