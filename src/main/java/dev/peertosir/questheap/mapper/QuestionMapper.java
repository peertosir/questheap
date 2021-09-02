package dev.peertosir.questheap.mapper;


import dev.peertosir.questheap.config.SpringMapperConfig;
import dev.peertosir.questheap.domain.Answer;
import dev.peertosir.questheap.domain.Question;
import dev.peertosir.questheap.domain.Quiz;
import dev.peertosir.questheap.dto.question.QuestionDto;
import dev.peertosir.questheap.exception.base.client.BadRequestException;
import dev.peertosir.questheap.exception.question.QuestionNotFoundException;
import dev.peertosir.questheap.repository.AnswerRepository;
import dev.peertosir.questheap.repository.QuestionRepository;
import dev.peertosir.questheap.repository.QuizRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = SpringMapperConfig.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public abstract class QuestionMapper {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Mapping(target = "quizId", source = "quiz.id")
    @Mapping(target = "answers", ignore = true)
    public abstract QuestionDto map(Question question);

    public abstract List<QuestionDto> map(List<Question> questions);

    public Question map(QuestionDto questionDto) {
        Question question = new Question();
        if (questionDto != null) {
            beforeQuestionMapping(question, questionDto);
            if (questionDto.getId() != 0) {
                question = questionRepository.findById(questionDto.getId()).orElseThrow(
                        () -> new QuestionNotFoundException("Question with id " + questionDto.getId() + " not found")
                );
            }
            question.setQuestionBody(questionDto.getQuestionBody());
            question.setQuestionTitle(questionDto.getQuestionTitle());
        }
        return question;
    }

    @BeforeMapping
    public void beforeQuestionToMapping(@MappingTarget QuestionDto target, Question source) {
        if (source.getAnswers() != null) {
            target.setAnswers(source.getAnswers().stream().map(Answer::getId).collect(Collectors.toSet()));
        }
    }

    protected void beforeQuestionMapping(@MappingTarget Question target, QuestionDto source) {
        Quiz quiz = quizRepository.findById(source.getQuizId()).orElseThrow(
                () -> new BadRequestException("Provided Quiz id: " + source.getQuizId() + " not found")
        );
        target.setQuiz(quiz);
        if (source.getAnswers() != null) {
            for (var answerId :
                    source.getAnswers()) {
                Answer foundAnswer = answerRepository.findById(answerId).orElseThrow(
                        () -> new BadRequestException("Provided Answer id: " + answerId + " not found")
                );
                target.getAnswers().add(foundAnswer);
            }
        }
    }
}
