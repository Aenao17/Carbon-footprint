package org.example;

import org.example.Domain.Question;
import org.example.Repository.*;
import org.example.Service.IService;
import org.example.Service.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

@SpringBootApplication
//@EntityScan(basePackages = {"org.example.Domain"})
public class Main  {


    public static final String URL = "http://localhost:8080";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String args[]) throws Exception {
        UserRepository userRepository = new UserHibernateRepository();
        QuestionRepository questionRepository = new QuestionHibernateRepository();
        SurveyRepository surveyRepository = new SurveyHibernateRepository();

        IService service = new Service(userRepository, questionRepository, surveyRepository);

        SpringApplication.run(Main.class, args);

//        User user = new User(2,"David", "123");
//        service.addUser(user);
//        Question question = new Question("ASAI CA MERGE");
//        service.addQuestion(question);
    }

    private static <T> T execute(Callable<T> callable) throws Exception {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}

