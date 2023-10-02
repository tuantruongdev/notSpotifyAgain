package com.people.connector.repositories.user;


import com.people.connector.models.User;
import com.people.connector.repositories.user.CustomUserRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>, CustomUserRepository {

}
