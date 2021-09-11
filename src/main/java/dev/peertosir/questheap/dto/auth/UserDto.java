package dev.peertosir.questheap.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @Length(min = 5)
    private String username;

    @Length(min = 8)
    private String password;
}
