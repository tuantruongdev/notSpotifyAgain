package com.people.connector.repositories.user;

import com.people.connector.models.User;

import java.util.List;

public interface CustomUserRepository {

    List<User> findUsersWithName(String name);
}
