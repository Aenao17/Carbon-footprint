package org.example.Service;


import org.example.Domain.User;
import org.example.Repository.QuestionRepository;
import org.example.Repository.SurveyRepository;
import org.example.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class RestService {

    UserRepository userRepository;
    QuestionRepository questionRepository;
    SurveyRepository surveyRepository;

    private Map<String, IObserver> loggedClients = new HashMap<>();
    ExecutorService executorService = Executors.newFixedThreadPool(5);


    public RestService(UserRepository userRepository, QuestionRepository questionRepository,
                       SurveyRepository surveyRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllGames() throws Exception {
        System.out.println("GET ALL USERS DIN REST SERVICE");
        return userRepository.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        System.out.println("Creating user...");
        userRepository.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userError(Exception e) {
        return e.getMessage();
    }
}
