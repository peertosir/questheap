package dev.peertosir.questheap.repository.auth;

import dev.peertosir.questheap.domain.auth.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(String name);
}
