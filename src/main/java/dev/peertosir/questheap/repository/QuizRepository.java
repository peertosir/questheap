package dev.peertosir.questheap.repository;

import dev.peertosir.questheap.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {}
