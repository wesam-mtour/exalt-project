package com.exalt.sparepartsmanagement.tests;

import com.exalt.sparepartsmanagement.repository.ProductRepository;
import com.exalt.sparepartsmanagement.model.Product;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductRepository repository;
    @Autowired
    EntityManager entityManager;
    @Test
    public void testCreate() {
        Product product = new Product();
        product.setOem("22");
        product.setName("AA");
        product.setCostPrice(1500);
        product.setSellingPrice(2000);
        product.setCarType("DAF");
        product.setQuantity(66);
        product.setProducers("wesam");
        repository.save(product);
    }

    // Read product from database
    @Test
    public void testRead() {
        Product product;
        product = repository.findByOem("22");
        assertNotNull(product);
        System.out.println(product);
        //  assertEquals("AA", product.getName());
    }

    // update specific product
    @Test
    public void testUpdate() {
        Product product;
        product = repository.findByOem("123");
        assertNotNull(product);
        product.setName("AB");
        product.setCostPrice(3000);
        product.setSellingPrice(2000);
        product.setCarType("VOLVO");
        product.setQuantity(77);
        product.setProducers("frontec");
        repository.save(product);
    }

    // delete specific product
    @Test
    public void testDelete() {
//        if (repository.existsById("22")) { // check the product existence
//            repository.deleteById("22");
//        }
    }

    //print the current number of rows "products", in database
    @Test
    public void testCount() {
        System.out.println("Total recodes :" + repository.count());
    }

    //find product by name
    @Test
    public void testFindByName() {
        Product product = repository.findByName("A");
        System.out.println(product.getCostPrice());

    }

    //find product by its OEM
    @Test
    public void testFindByOem() {
        Product product = repository.findByOem("25");
        System.out.println(product.getCostPrice());

    }

    //find product by name and its OEM
    @Test
    public void testFindByNameAndOem() {
        Product product = repository.findByNameAndOem("B", "23");
        System.out.println(product.getCostPrice());

    }

    @Test
    public void testFindByNameAndOemNQ() {
        Product product = repository.findByNameAndOemNQ("B", "23");
        System.out.println(product.getCostPrice());

    }

    @Test
    public void findProductForGivenPriceNQ() {
        List<Product> products = repository.findProductForGivenPriceNQ(33, 36);
        products.forEach(product -> System.out.println(product.getName()));
    }

    @Transactional // enable level 1 caching in hibernate session level
    @Test
    public void testCache() {
        Session session = entityManager.unwrap(Session.class);
        Product product;

        product = repository.findByOem("22");
        System.out.println(product.getName());

        session.evict(product); // evict is used to delete the object from cache "level 1 cache"

        product = repository.findByOem("22");
        System.out.println(product.getName());
    }


}
