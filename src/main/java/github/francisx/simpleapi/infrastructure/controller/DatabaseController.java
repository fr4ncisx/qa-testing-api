package github.francisx.simpleapi.infrastructure.controller;

import github.francisx.simpleapi.domain.dto.ProductRequest;
import github.francisx.simpleapi.domain.model.Product;
import github.francisx.simpleapi.domain.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Products Controller (MySQL)", description = "Este controlador accede a datos directo de MySQL y no en memoria")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/db/")
public class DatabaseController{
    private final IProductService productService;

    @Operation(summary = "Find all products",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "returns all elements from database as a list",
                            content =
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404",
                            description = "No hay elementos en la base de datos",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "400",
                            description = "Solicitud incorrecta",
                            content = @Content(schema = @Schema()))
            })
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        var products = productService.getAllProducts();
        return products.isEmpty() ? ResponseEntity.status(404).build() : ResponseEntity.ok(products);
    }

    @Operation(summary = "Find product by id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Devuelve el producto encontrado",
                            content =
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Solicitud incorrecta",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "404",
                            description = "Product no encontrado",
                            content = @Content(schema = @Schema()))
            })
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable UUID id) {
        var product = productService.getProduct(id);
        return product == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(product);
    }

    @Operation(summary = "Find all products ordered",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "returns all elements from database ordered by price",
                            content =
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404",
                            description = "No hay elementos en la base de datos",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "400",
                            description = "Solicitud incorrecta",
                            content = @Content(schema = @Schema()))
            })
    @GetMapping("/ordered")
    public ResponseEntity<List<Product>> getProductsOrderedByPrice() {
        var products = productService.getProductsOrdered();
        return products.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(products);
    }

    @Operation(summary = "Find product by name",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Devuelve el producto encontrado",
                            content =
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Solicitud incorrecta",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "404",
                            description = "Product no encontrado",
                            content = @Content(schema = @Schema()))
            })
    @GetMapping("/by-name")
    public ResponseEntity<Product> getByName(@RequestParam String name) {
        var product = productService.getProductByName(name);
        return product == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(product);
    }

    @Operation(summary = "Create new product",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "Product saved in database",
                            content =
                            @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "400",
                            description = "Solicitud incorrecta",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "401",
                            description = "Rol no autorizado, token expirado",
                            content = @Content(schema = @Schema()))
            })
    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductRequest productRequest,
                                              @RequestHeader(name = "Authorization") String token){
        productService.createProduct(productRequest, token);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete product by id",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "Product deleted from database",
                            content =
                            @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "400",
                            description = "Solicitud incorrecta",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "401",
                            description = "Rol no autorizado, token expirado",
                            content = @Content(schema = @Schema()))
            })
    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID uuid,
                                              @RequestHeader(name = "Authorization") String token){
        productService.deleteProduct(uuid, token);
        return ResponseEntity.noContent().build();
    }
}
