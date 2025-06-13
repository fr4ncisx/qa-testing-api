package github.francisx.simpleapi.infrastructure.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoleNotAllowedEx extends RuntimeException{
    private final String message;
}
