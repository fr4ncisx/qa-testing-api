package github.francisx.simpleapi.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ProductRequest {
    @NotBlank @NotNull
    private String name;
    @NotBlank @NotNull
    private String description;
    @NotNull @Positive
    private BigDecimal price;
}
