package dev.peertosir.questheap.repository;

import dev.peertosir.questheap.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
