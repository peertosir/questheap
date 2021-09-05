package dev.peertosir.questheap.mapper;

import dev.peertosir.questheap.config.SpringMapperConfig;
import dev.peertosir.questheap.domain.Answer;
import dev.peertosir.questheap.dto.answer.AnswerDto;
import dev.peertosir.questheap.exception.question.QuestionNotFoundException;
import dev.peertosir.questheap.repository.QuestionRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(config = SpringMapperConfig.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public abstract class AnswerMapper {

    @Autowired
    private QuestionRepository questionRepository;

    @Mapping(target = "questionId", source = "question.id")
    public abstract AnswerDto map(Answer answer);

    @Mapping(target = "question", ignore = true)
    public abstract Answer map(AnswerDto answerDto);

    @BeforeMapping
    public void beforeAnswerMapping(@MappingTarget Answer target, AnswerDto source) {
        target.setQuestion(questionRepository.findById(source.getQuestionId()).orElseThrow(
                () -> new QuestionNotFoundException("Question with id: " + source.getQuestionId() + " not found")
        ));
    }
}
