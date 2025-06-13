package github.francisx.simpleapi.infrastructure.security.persistence;

import github.francisx.simpleapi.infrastructure.security.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, UUID> {
    @Modifying
    @Query("UPDATE AccessToken t SET t.expired = true WHERE t.expired = false AND t.expirationTime < :actualTime")
    void updateExpiredTokens(@Param(value = "actualTime") LocalDateTime actualTime);
    boolean existsByTokenAndExpiredIsFalse(String token);
    AccessToken findByToken(String token);
}
