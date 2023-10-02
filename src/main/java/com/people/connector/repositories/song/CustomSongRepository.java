package com.people.connector.repositories.song;

import com.people.connector.models.Song;
import com.people.connector.models.User;

import java.util.List;

public interface CustomSongRepository {
    List<Song> findSongWithName(String name);
}
