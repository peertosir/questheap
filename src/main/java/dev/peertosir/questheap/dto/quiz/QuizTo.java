package dev.peertosir.questheap.dto.quiz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.peertosir.questheap.domain.Question;
import dev.peertosir.questheap.domain.Quiz;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

public class QuizTo {
    private long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private Set<Long> questions;

    @JsonCreator
    public QuizTo(
            @JsonProperty("id") long id,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("questions") Set<Long> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Long> questions) {
        this.questions = questions;
    }

    public static QuizTo fromQuiz(Quiz quiz) {
         return new QuizTo(
                 quiz.getId(),
                 quiz.getTitle(),
                 quiz.getDescription(),
                 quiz.getQuestions().stream().map(Question::getId).collect(Collectors.toSet())
         );
    }
}
