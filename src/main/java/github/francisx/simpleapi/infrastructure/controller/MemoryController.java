package github.francisx.simpleapi.infrastructure.controller;

import github.francisx.simpleapi.domain.model.LocalProduct;
import github.francisx.simpleapi.infrastructure.persistence.InMemoryDatabase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Products Controller (Memory DB)", description = "Este controlador accede a datos que estan en memoria y son inmutables")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mem/")
public class MemoryController {

    private final InMemoryDatabase databaseRepository;

    @Operation(
            summary = "Finds one specific product",
            description = "Finds one product from in-memory database",
            responses = {
                    @ApiResponse(description = "Devuelve un producto", responseCode = "200"),
                    @ApiResponse(description = "Elemento no encontrado", responseCode = "404")}
    )
    @GetMapping
    public ResponseEntity<LocalProduct> findOne(@RequestParam String name){
        return databaseRepository.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Finds all products",
            description = "Finds all products from in-memory database",
            responses = {
                    @ApiResponse(description = "Devuelve una lista de productos", responseCode = "200"),
                    @ApiResponse(description = "Elemento no encontrado", responseCode = "404")}
    )
    @GetMapping("/all")
    public ResponseEntity<List<LocalProduct>> findAllProducts(){
        return ResponseEntity.ok(databaseRepository.findAll());
    }

    @Operation(
            summary = "Finds all products ordered",
            description = "Find a list of all products ordered by max price",
            responses = {
                    @ApiResponse(description = "Devuelve una lista de productos ordenados por mayor precio", responseCode = "200"),
                    @ApiResponse(description = "Elemento no encontrado", responseCode = "404")}
    )
    @GetMapping("/ordered")
    public ResponseEntity<List<LocalProduct>> findAllProductsOrdered(){
        return ResponseEntity.ok(databaseRepository.findAllOrderedByPrice());
    }

}
