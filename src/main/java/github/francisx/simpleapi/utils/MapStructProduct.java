package github.francisx.simpleapi.utils;

import github.francisx.simpleapi.domain.dto.ProductRequest;
import github.francisx.simpleapi.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructProduct extends IMapStruct<Product, ProductRequest> {
}
