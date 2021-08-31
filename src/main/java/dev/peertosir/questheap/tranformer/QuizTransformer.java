package dev.peertosir.questheap.tranformer;

import dev.peertosir.questheap.domain.Quiz;
import dev.peertosir.questheap.dto.quiz.QuizTo;
import dev.peertosir.questheap.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizTransformer {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizTransformer(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz toQuiz(QuizTo quizTo) {
        Quiz quiz = new Quiz();
        if (quizTo.getId() != 0) {
            quiz = quizRepository.getById(quizTo.getId());
        }
        quiz.setDescription(quizTo.getDescription());
        quiz.setTitle(quizTo.getTitle());
        return quiz;
    }
}
