package org.example.Repository;

import org.example.Domain.User;

public interface UserRepository extends Repository<Integer, User> {

    void add(User user);
    void update(User user);

    User getByCredentials(String username, String password);
}
