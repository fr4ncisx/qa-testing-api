package github.francisx.simpleapi.infrastructure.security.model;

import github.francisx.simpleapi.infrastructure.security.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(length = 512)
    private String token;
    private LocalDateTime expirationTime;
    private boolean expired;
    @Enumerated(EnumType.STRING)
    private Role role;
}
