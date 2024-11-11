package org.example.Service;

import org.example.Domain.Question;
import org.example.Domain.User;

import java.util.List;

public interface IService {
    User login(String username, String password, IObserver iobs) throws Exception;
    List<User> getAllUsers() throws Exception;

    void logout(User user, IObserver client) throws Exception;



    void addUser(User user);
    void addQuestion(Question question);
}
