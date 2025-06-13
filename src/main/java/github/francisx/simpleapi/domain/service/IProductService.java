package github.francisx.simpleapi.domain.service;

import github.francisx.simpleapi.domain.dto.ProductRequest;
import github.francisx.simpleapi.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    Product getProduct(UUID id);
    Product getProductByName(String name);
    List<Product> getAllProducts();
    List<Product> getProductsOrdered();
    void createProduct(ProductRequest productRequest, String token);
    void deleteProduct(UUID uuid, String token);
}
