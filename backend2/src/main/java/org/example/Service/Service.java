package org.example.Service;


import org.example.Domain.User;
import org.example.Repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements IService {

    UserRepository userRepository;
//    WordRepository wordRepository;
//    GameRepository gameRepository;

    private Map<String, IObserver> loggedClients = new HashMap<>();
    ExecutorService executorService = Executors.newFixedThreadPool(5);


    public Service(UserRepository userRepository){//, WordRepository wordRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
//        this.wordRepository = wordRepository;
//        this.gameRepository = gameRepository;
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
    public void addUser(User user) { // S-ar putea sa avem nevoie si de un antet cu atomii user (string username ...)
//        int id = userRepository.getAll().size() + 1;
        userRepository.add(user);
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
