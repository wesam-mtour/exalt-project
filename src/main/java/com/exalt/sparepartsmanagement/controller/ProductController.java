package com.exalt.sparepartsmanagement.controller;

import com.exalt.sparepartsmanagement.dto.ProductDTO;
import com.exalt.sparepartsmanagement.mapper.ProductMapper;
import com.exalt.sparepartsmanagement.service.ProductService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
Creating RESTful web services using Spring MVC
 */
@RestController
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    /*
    To inject the object productService, implicitly
     */
    @Autowired
    private ProductService productService;

    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @GetMapping(value = "/api/v1/products", params = {"page", "pageSize"})
    public List<ProductDTO> getProducts(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        logger.info("product controller method -getProducts");
        return productService.getAll(page, pageSize);
    }

    @GetMapping(value = "/api/v1/products/{oemOrProductName}")
    public ProductDTO getSpecificProduct(@PathVariable String oemOrProductName) {
        logger.info("product controller method -getSpecificProduct");
        return productService.get(oemOrProductName);
    }

    @PostMapping(value = "/api/v1/products")
    public String createNewProduct(@Valid @RequestBody ProductDTO productDTO) {
        logger.info("product controller method -createNewProduct");
        productService.save(productDTO);
        return "product saved successfully ";
    }

    @PutMapping(value = "/api/v1/products/{oemOrProductName}")
    public String updateProduct(@PathVariable String oemOrProductName, @RequestBody ProductDTO productDTO) {
        logger.info("product controller method -updateProduct");
        productService.update(oemOrProductName, productDTO);
        return "product updated successfully ";
    }

    @DeleteMapping(value = "/api/v1/products/{oemOrProductName}")
    public String deleteProduct(@PathVariable String oemOrProductName) {
        logger.info("product controller method -deleteProduct");
        productService.delete(oemOrProductName);
        return " product deleted successfully";
    }
}
