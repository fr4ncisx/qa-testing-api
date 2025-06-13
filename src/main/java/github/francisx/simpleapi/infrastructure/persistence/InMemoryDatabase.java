package github.francisx.simpleapi.infrastructure.persistence;

import github.francisx.simpleapi.domain.model.LocalProduct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryDatabase {
    List<LocalProduct> inMemoryDB = List.of(
            new LocalProduct("Silbato", "Solo para uso en fútbol", BigDecimal.valueOf(7.62)),
            new LocalProduct("Sillón", "Muy cómodo para la ocasión", BigDecimal.valueOf(875.96)),
            new LocalProduct("Casa", "Pequeño pero habitable", BigDecimal.valueOf(78500)),
            new LocalProduct("Auto", "Mejor que transporte público", BigDecimal.valueOf(32042.63))
    );

    public List<LocalProduct> findAll(){
        return inMemoryDB;
    }

    public Optional<LocalProduct> findByName(String name){
        return inMemoryDB.stream()
                .filter(db -> db.getName().equals(name))
                .findAny();
    }

    public List<LocalProduct> findAllOrderedByPrice(){
        return inMemoryDB.stream()
                .sorted(Comparator.comparing(LocalProduct::getPrice).reversed())
                .toList();
    }
}
