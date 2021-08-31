package dev.peertosir.questheap.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue
    private long id;
    private String questionTitle;
    private String questionBody;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    private Set<Answer> answers;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    public Question() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public long getId() {
        return id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && questionTitle.equals(question.questionTitle) && Objects.equals(questionBody, question.questionBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionTitle, questionBody);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionBody='" + questionBody + '\'' +
                '}';
    }
}
