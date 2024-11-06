package org.example;

import org.example.Domain.User;
import org.example.Repository.UserHibernateRepository;
import org.example.Repository.UserRepository;
import org.example.Service.IService;
import org.example.Service.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

@SpringBootApplication
public class Main  {

    public static final String URL = "http://localhost:8080";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String args[]) throws Exception {
        UserRepository userRepository = new UserHibernateRepository(); // Pentru fiecare Entity nou rescriem aici
        IService service = new Service(userRepository);

//
//        System.out.println(service.getAllUsers());
        SpringApplication.run(Main.class, args);

//        User user = new User(1,"David", "123");
//        execute(() -> restTemplate.postForObject(URL, user, User.class));
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
