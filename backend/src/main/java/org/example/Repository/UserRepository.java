package org.example.Repository;

import org.example.Domain.User;

public interface UserRepository extends Repository<Integer, User> {
    User getByCredentials(String username, String password);
}
