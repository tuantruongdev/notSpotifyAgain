package com.people.connector.repositories.file;


import com.people.connector.models.FileStorage;
import com.people.connector.models.User;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<FileStorage, Integer>, CustomFileRepository {

}
