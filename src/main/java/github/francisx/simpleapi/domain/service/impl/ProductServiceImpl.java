package github.francisx.simpleapi.domain.service.impl;

import github.francisx.simpleapi.domain.dto.ProductRequest;
import github.francisx.simpleapi.domain.model.Product;
import github.francisx.simpleapi.domain.service.IProductService;
import github.francisx.simpleapi.infrastructure.exception.error.SQLNotFoundEx;
import github.francisx.simpleapi.infrastructure.persistence.ProductRepository;
import github.francisx.simpleapi.infrastructure.security.model.enums.Role;
import github.francisx.simpleapi.infrastructure.security.service.ITokenService;
import github.francisx.simpleapi.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final ITokenService tokenService;

    @Override
    public Product getProduct(UUID id) {
        return productRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name)
                .orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsOrdered() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    @Transactional
    @Override
    public void createProduct(ProductRequest productRequest, String token) {
        var accessToken = tokenService.validateAndGet(token);
        tokenService.allowedRoles(accessToken.getRole(), Role.ADMIN, Role.AGENT, Role.EMPLOYEE);
        productRepository.save(MapperUtils.convertDTO(productRequest, Product.class));
    }

    @Transactional
    @Override
    public void deleteProduct(UUID uuid, String token) {
        var accessToken = tokenService.validateAndGet(token);
        tokenService.allowedRoles(accessToken.getRole(), Role.ADMIN);
        var product = productRepository.findById(uuid)
                .orElseThrow(() -> new SQLNotFoundEx("Not found in database"));
        productRepository.delete(product);
    }
}
