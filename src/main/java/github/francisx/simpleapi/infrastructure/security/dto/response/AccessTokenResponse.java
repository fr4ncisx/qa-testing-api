package github.francisx.simpleapi.infrastructure.security.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.francisx.simpleapi.infrastructure.security.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessTokenResponse {
    private String token;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime expirationTime;
    private Role role;
}
