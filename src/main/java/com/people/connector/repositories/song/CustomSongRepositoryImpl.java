package com.people.connector.repositories.song;

import com.people.connector.models.Song;
import com.people.connector.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomSongRepositoryImpl implements CustomSongRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Song> findSongWithName(String name) {
        TypedQuery<Song> query =entityManager.createQuery("""
                select u from User u where u.realName = :name
                """,Song.class).setParameter("name",name);
        return query.getResultList();
    }
}
