package com.exalt.sparepartsmanagement.service;

import com.exalt.sparepartsmanagement.dto.ProductDTO;
import java.util.List;


public interface ProductService {
    List<ProductDTO> getAll(int page, int pageSize);

    ProductDTO get(String omeOrProductName);

    void save(ProductDTO productDTO);

    void delete(String omeOrProductName);

    void update(String omeOrProductName, ProductDTO product);

}
