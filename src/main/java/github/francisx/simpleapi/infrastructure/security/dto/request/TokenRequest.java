package github.francisx.simpleapi.infrastructure.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenRequest {
    @NotNull @NotBlank
    private String secretKey;
}
