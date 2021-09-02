package dev.peertosir.questheap.mapper;

import dev.peertosir.questheap.config.SpringMapperConfig;
import dev.peertosir.questheap.domain.Question;
import dev.peertosir.questheap.domain.Quiz;
import dev.peertosir.questheap.dto.quiz.QuizTo;
import dev.peertosir.questheap.exception.quiz.QuizNotFoundException;
import dev.peertosir.questheap.repository.QuizRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = SpringMapperConfig.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class QuizMapper {

    @Autowired
    private QuizRepository quizRepository;

    public Quiz map(QuizTo quizTo) {
        Quiz quiz = new Quiz();

        if (quizTo != null) {
            if (quizTo.getId() != 0) {
                quiz = quizRepository.findById(quizTo.getId()).orElseThrow(
                        () -> new QuizNotFoundException("Quiz with id " + quizTo.getId() + " not found")
                );
            }
            quiz.setTitle(quizTo.getTitle());
            quiz.setDescription(quizTo.getDescription());
        }

        return quiz;
    }

    @Mapping(target = "questions", ignore = true)
    public abstract QuizTo map(Quiz quiz);

    public abstract List<QuizTo> map(List<Quiz> quizzes);

    @BeforeMapping
    public void beforeQuizToMapping(@MappingTarget QuizTo target, Quiz source) {
        if (source.getQuestions() != null) {
            target.setQuestions(source.getQuestions().stream().map(Question::getId).collect(Collectors.toSet()));
        }
    }
}
