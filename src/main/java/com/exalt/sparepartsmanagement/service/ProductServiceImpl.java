package com.exalt.sparepartsmanagement.service;

import com.exalt.sparepartsmanagement.dto.ProductDTO;
import com.exalt.sparepartsmanagement.mapper.ProductMapper;
import com.exalt.sparepartsmanagement.repository.EmployeeRepository;
import com.exalt.sparepartsmanagement.repository.ProductRepository;
import com.exalt.sparepartsmanagement.error.ConflictExceptions;
import com.exalt.sparepartsmanagement.error.NotFoundExceptions;
import com.exalt.sparepartsmanagement.model.Product;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Transactional(readOnly = true)
    public List<ProductDTO> getAll(int page, int pageSize) {
        if (page < 1) {
            throw new NotFoundExceptions("Invalid page number");
        }
        if (pageSize < 1) {
            throw new NotFoundExceptions("Invalid page size number");
        }

        Pageable paging = PageRequest.of((page - 1) * pageSize, pageSize);
        Page<Product> pagedResult = productRepository.findAll(paging);
        return productMapper.productsToDTOs(pagedResult.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO get(String omeOrProductName) {
        Product product = productRepository.findByNameOrOemNQ(omeOrProductName);
        if (product == null) {
            throw new NotFoundExceptions("Product not Found");
        } else
            return productMapper.productToProductDTO(product);
    }

    @Transactional
    public Product save(ProductDTO productDTO) {
        String temp = productRepository.findByNameNQ(productDTO.getName());
        if (temp != null) {
            throw new ConflictExceptions(String.format("There is product with the same name ( %s ) ", temp));
        }
        temp = productRepository.findNameByOemNQ(productDTO.getOem());
        if (temp != null) {
            throw new ConflictExceptions(String.format("This OEM related to product ( %s )", temp));
        }
        return productRepository.save(productMapper.DTOToProduct(productDTO));
    }

    @Transactional
    public void delete(String omeOrProductName) {
        Product product = productRepository.findByNameOrOemNQ(omeOrProductName);

        if (product != null) {
            productRepository.delete(product);
        } else
            throw new NotFoundExceptions("Not found product to deleting ");
    }

    @Transactional
    public void update(String omeOrProductName, ProductDTO productDTO) {
        /*
        check if the product not present at all
         */
        Product updatingProduct = productRepository.findByNameOrOemNQ(omeOrProductName);
        if (updatingProduct != null) {
            /*
             * check if the name and OEM number remains the same then no conflict
             * if they are changed, but there should be no conflict with and existing name or OEM
             */
            String temp = productRepository.findByNameNQ(productDTO.getName());
            if (temp != null && !(temp.equals(updatingProduct.getName()))) {
                throw new ConflictExceptions(String.format("There is product with the same name ( %s )", temp));
            }
            temp = productRepository.findNameByOemNQ(productDTO.getOem());
            if (temp != null && !(temp.equals(updatingProduct.getName()))) {
                throw new ConflictExceptions(String.format("This OEM related to product ( %s )", temp));
            }
            /*
            updating product ..
             */
            updatingProduct.setCostPrice(productDTO.getCostPrice());
            updatingProduct.setCarType(productDTO.getCarType());
            updatingProduct.setName(productDTO.getName());
            updatingProduct.setOem(productDTO.getOem());
            updatingProduct.setProducers(productDTO.getProducers());
            updatingProduct.setQuantity(productDTO.getQuantity());
            updatingProduct.setSellingPrice(productDTO.getSellingPrice());

            productRepository.save(updatingProduct);
        } else
            throw new NotFoundExceptions("Not found product to updating ");
    }
}
