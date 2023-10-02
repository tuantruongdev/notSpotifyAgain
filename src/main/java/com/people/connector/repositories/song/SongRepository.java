package com.people.connector.repositories.song;

import com.people.connector.models.Song;
import com.people.connector.repositories.user.CustomUserRepository;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Integer>, CustomUserRepository {

}
