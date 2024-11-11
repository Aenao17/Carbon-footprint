package org.example.Domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.util.List;
import java.util.Map;

@jakarta.persistence.Entity
@Table(name = "Surveys")
public class Survey implements org.example.Domain.Entity<Integer> {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "questionAnswers")
    private String questionAnswersJson;

    @Transient
    private Map<Integer, List<String>> questionAnswers;

    private static final Gson gson = new Gson();

    public Survey(Integer id, Integer userId, Map<Integer, List<String>> questionAnswers) {
        this.id = id;
        this.userId = userId;
        setQuestionAnswers(questionAnswers); // Convert map to JSON
    }

    public Survey() {
        // Default constructor for JPA
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Map<Integer, List<String>> getQuestionAnswers() {
        if (questionAnswers == null && questionAnswersJson != null) {
            // Deserialize JSON to Map if not already done
            questionAnswers = gson.fromJson(questionAnswersJson, new TypeToken<Map<Integer, List<String>>>(){}.getType());
        }
        return questionAnswers;
    }

    public void setQuestionAnswers(Map<Integer, List<String>> questionAnswers) {
        this.questionAnswers = questionAnswers;
        this.questionAnswersJson = gson.toJson(questionAnswers);
    }
}
