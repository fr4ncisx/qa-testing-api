package github.francisx.simpleapi.infrastructure.security.service;

import github.francisx.simpleapi.infrastructure.security.dto.response.AccessTokenResponse;
import github.francisx.simpleapi.infrastructure.security.model.AccessToken;
import github.francisx.simpleapi.infrastructure.security.model.enums.Role;

public interface ITokenService {

    AccessTokenResponse createToken(String secretKey);

    void validateToken(String token);

    AccessToken validateAndGet(String token);

    void allowedRoles(Role role, Role...allowed);
}
