package com.exalt.sparepartsmanagement.repository;

import com.exalt.sparepartsmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    Product findByNameAndOem(String name, String oem);

    @Query(value = "select * from product p where p.oem=:oemOrProductName or p.name=:oemOrProductName ", nativeQuery = true)
    Product findByNameOrOemNQ(@Param("oemOrProductName") String oemOrProductName);

    /*
      jpql query using class name "Product" insteadof table name in database
     */
    @Query("from Product p where p.oem=:oem")
    Product findByOem(@Param("oem") String oem);

    /*
         native query using table name as in database "product" insteadof class name
      */
    @Query(value = "select * from product p where p.oem=:oem and p.name=:productName", nativeQuery = true)
    Product findByNameAndOemNQ(@Param("productName") String name, @Param("oem") String oem);

    @Query(value = "select * from product p where p.cost_price>=:min and p.cost_price<=:max", nativeQuery = true)
    List <Product> findProductForGivenPriceNQ(@Param("min") double min, @Param("max") double max);

    @Query(value = "select p.selling_price from product p where p.oem=:oem", nativeQuery = true)
    double getProductSellingPriceNQ(@Param("oem") String oem);

    /*
    Updating queries MUST be transactional
    */
    @Transactional
    /*
    to enhance the @Query annotation to execute not only SELECT queries but also INSERT, UPDATE and DELETE queries.
    */
    @Modifying
    @Query(value = " UPDATE product p SET p.quantity=p.quantity-:quantity  WHERE p.oem=:oem", nativeQuery = true)
    void setQuantityNQ(@Param("quantity") int quantity, @Param("oem") String oem);

    @Query(value = "select p.name from product p where p.name=:name", nativeQuery = true)
    String findByNameNQ(String name);

    @Query(value = "select p.name from  product p where p.oem=:oem", nativeQuery = true)
    String findNameByOemNQ(String oem);

}
