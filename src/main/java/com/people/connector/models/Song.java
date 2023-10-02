package com.people.connector.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long authorId;
    private String backgroundVideo;
    private long lyricsId;
    private ArrayList<Integer> genreList;

    public Song(long id, String name, long authorId, String backgroundVideo, long lyricsId, ArrayList<Integer> genreList) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.backgroundVideo = backgroundVideo;
        this.lyricsId = lyricsId;
        this.genreList = genreList;
    }

    public Song(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getBackgroundVideo() {
        return backgroundVideo;
    }

    public void setBackgroundVideo(String backgroundVideo) {
        this.backgroundVideo = backgroundVideo;
    }

    public long getLyricsId() {
        return lyricsId;
    }

    public void setLyricsId(long lyricsId) {
        this.lyricsId = lyricsId;
    }

    public ArrayList<Integer> getGenreList() {
        return genreList;
    }

    public void setGenreList(ArrayList<Integer> genreList) {
        this.genreList = genreList;
    }
}
