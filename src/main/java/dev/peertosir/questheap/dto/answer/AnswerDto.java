package dev.peertosir.questheap.dto.answer;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class AnswerDto {
    private long id;
    @NotNull
    private String text;
    @NotNull
    private long questionId;
    @NotNull
    private boolean isCorrect;
}
