package com.exalt.sparepartsmanagement.mapper;

import com.exalt.sparepartsmanagement.dto.ProductDTO;
import com.exalt.sparepartsmanagement.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

/*
The @Mapper annotation causes the MapStruct code generator to create an
implementation of the ProductMapper interface during build-time.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDTO> productsToDTOs(List<Product> products);

    Product DTOToProduct(ProductDTO productDTO);

     ProductDTO productToProductDTO(Product product);

    }
