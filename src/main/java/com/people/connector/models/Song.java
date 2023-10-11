package com.people.connector.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ColumnDefault("1")
    private long uploaderId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "file_id")
    private FileStorage file;

    @Transient
    private long fileId;
    private long authorId;
    private String backgroundVideo;
    @ColumnDefault("1")
    private long lyricsId;
    private ArrayList<Integer> genreList;

    @CreationTimestamp
    private long createAt;
    @UpdateTimestamp
    private long updateAt;


    public Song(){}

    public Song(long id, String name, long authorId, String backgroundVideo, long lyricsId, ArrayList<Integer> genreList, long createAt, Long updateAt) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.backgroundVideo = backgroundVideo;
        this.lyricsId = lyricsId;
        this.genreList = genreList;
    }

    public FileStorage getFile() {
        return file;
    }

    public void setFile(FileStorage file) {
        this.file = file;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(long uploaderId) {
        this.uploaderId = uploaderId;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
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

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }
}
