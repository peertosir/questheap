package dev.peertosir.questheap.dto.question;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class QuestionDto {
    private long id;
    @NotNull
    private String questionTitle;
    private String questionBody;
    private Set<Long> answers;
    @NotNull
    private long quizId;
}
