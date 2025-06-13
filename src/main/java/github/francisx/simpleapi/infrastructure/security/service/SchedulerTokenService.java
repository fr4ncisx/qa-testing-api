package github.francisx.simpleapi.infrastructure.security.service;

import github.francisx.simpleapi.infrastructure.security.persistence.AccessTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@EnableScheduling
@RequiredArgsConstructor
@Service
public class SchedulerTokenService {

    private final AccessTokenRepository tokenRepository;

    @Transactional
    @Scheduled(fixedRate = 300000)
    protected void disableExpiredTokens(){
        tokenRepository.updateExpiredTokens(LocalDateTime.now());
    }
}
