package github.francisx.simpleapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LocalProduct {
    private String name;
    private String description;
    private BigDecimal price;
}
