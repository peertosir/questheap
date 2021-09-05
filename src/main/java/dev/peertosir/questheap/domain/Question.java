package dev.peertosir.questheap.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String questionTitle;
    private String questionBody;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

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

    public void deleteAnswer(Answer answer) {
        this.answers.remove(answer);
    }
}
