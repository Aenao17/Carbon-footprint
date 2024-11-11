package org.example.Service;


import org.example.Domain.Question;
import org.example.Domain.User;
import org.example.Repository.QuestionRepository;
import org.example.Repository.SurveyRepository;
import org.example.Repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {

    UserRepository userRepository;
    QuestionRepository questionRepository;
    SurveyRepository surveyRepository;

    private Map<String, IObserver> loggedClients = new HashMap<>();
    ExecutorService executorService = Executors.newFixedThreadPool(5);


    public Service(UserRepository userRepository, QuestionRepository questionRepository,
                   SurveyRepository surveyRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }

    public User login(String username, String password, IObserver iobs){
        User user = userRepository.getByCredentials(username, password);
        if(user != null) {
            loggedClients.put(user.getUsername(), iobs);
            return user;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return userRepository.getAll();
    }

    @Override
    public void logout(User user, IObserver client) throws Exception {}

    @Override
    public void addUser(User user) {
//        int id = userRepository.getAll().size() + 1;
        userRepository.add(user);
        executorService.submit(() -> {
            for (IObserver client : loggedClients.values()) {
                client.update();
            }
        });
    }

    @Override
    public void addQuestion(Question question) {
        questionRepository.add(question);
        executorService.submit(() -> {
            for (IObserver client : loggedClients.values()) {
                client.update();
            }
        });
    }

//    @Override
//    public void AddBug(String name, String desc) {
//
//        int id = bugRepository.getAll().size() + 1;
//
//        Bug bug = new Bug(id, name, desc, BugStatus.PENDING);
//        bugRepository.add(bug);
//
//        executorService.submit(() -> {
//            for (IObserver client : loggedClients.values()) {
//                client.update();
//            }
//        });
//
//    }
}
