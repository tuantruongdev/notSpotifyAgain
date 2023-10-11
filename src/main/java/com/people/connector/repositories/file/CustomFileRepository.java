package com.people.connector.repositories.file;

import com.people.connector.models.FileStorage;
import com.people.connector.models.User;

import java.util.List;

public interface CustomFileRepository {

    FileStorage getFileById(long id);
}
