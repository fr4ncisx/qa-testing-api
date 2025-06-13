package github.francisx.simpleapi.infrastructure.security.service.impl;

import github.francisx.simpleapi.infrastructure.exception.error.RoleNotAllowedEx;
import github.francisx.simpleapi.infrastructure.exception.error.UnauthorizedEx;
import github.francisx.simpleapi.infrastructure.security.dto.response.AccessTokenResponse;
import github.francisx.simpleapi.infrastructure.security.model.AccessToken;
import github.francisx.simpleapi.infrastructure.security.model.enums.Role;
import github.francisx.simpleapi.infrastructure.security.persistence.AccessTokenRepository;
import github.francisx.simpleapi.infrastructure.security.service.ITokenService;
import github.francisx.simpleapi.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements ITokenService {

    @Value("${secret.key}")
    private String serverSecretKey;
    @Value("${token.expiration.time}")
    private Long minutes;

    private final AccessTokenRepository tokenRepository;

    @Transactional
    @Override
    public AccessTokenResponse createToken(String secretKey){
        validateSecret(secretKey);
        var expirationTime = LocalDateTime.now().plusMinutes(minutes);
        AccessToken token = new AccessToken(null, randomizeText(512), expirationTime, false, randomizeRole());
        tokenRepository.save(token);
        return MapperUtils.convertDTO(token, AccessTokenResponse.class);
    }

    @Override
    public void validateToken(String token){
        if(!tokenRepository.existsByTokenAndExpiredIsFalse(token))
            throw new UnauthorizedEx("Token is expired");
    }

    @Override
    public AccessToken validateAndGet(String token) {
        validateToken(token);
        return tokenRepository.findByToken(token);
    }

    @Override
    public void allowedRoles(Role role, Role...allowed) {
        List<Role> whiteList = Arrays.asList(allowed);
        var exists = whiteList.stream()
                .anyMatch(r -> r.equals(role));
        if(!exists)
            throw new RoleNotAllowedEx("Role " + role.toString() + " not allowed");
    }

    /**
     *
     * @param length better using length above 256
     * @return random text
     */
    private String randomizeText(int length) {
        SecureRandom secureRandom = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(secureRandom.nextInt(characters.length())));
        }
        return sb.toString();
    }

    private void validateSecret(String key){
        if(!serverSecretKey.equals(key))
            throw new UnauthorizedEx("Invalid secret key");
    }

    private Role randomizeRole(){
        SecureRandom secureRandom = new SecureRandom();
        var role = Role.values();
        var index = secureRandom.nextInt(role.length);
        return role[index];
    }
}
