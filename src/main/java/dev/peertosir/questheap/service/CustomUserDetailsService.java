package dev.peertosir.questheap.service;

import dev.peertosir.questheap.config.auth.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {
    @Override
    CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
