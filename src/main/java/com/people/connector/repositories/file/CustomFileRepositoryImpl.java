package com.people.connector.repositories.file;

import com.people.connector.models.FileStorage;
import com.people.connector.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomFileRepositoryImpl implements CustomFileRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public FileStorage getFileById(long id) {
        TypedQuery<FileStorage> query =entityManager.createQuery("""
                select f from FileStorage f where f.id = :id
                """,FileStorage.class).setParameter("id",id);
        return query.getSingleResult();
    }
}
