package com.exalt.sparepartsmanagement.tests;

import com.exalt.sparepartsmanagement.dto.ProductDTO;
import com.exalt.sparepartsmanagement.mapper.ProductMapper;
import com.exalt.sparepartsmanagement.repository.ProductRepository;
import org.junit.Assert;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    EntityManager entityManager;

    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);


    @LocalServerPort
    int localPort = 8081;

    HttpHeaders headers = new HttpHeaders();
    TestRestTemplate restTemplate = new TestRestTemplate();


//    @BeforeEach
//    public void create() {
//        ProductDTO productDTo = new ProductDTO();
//        productDTo.setOem("22");
//        productDTo.setName("AA");
//        productDTo.setCostPrice(1500);
//        productDTo.setSellingPrice(2000);
//        productDTo.setCarType("DAF");
//        productDTo.setQuantity(66);
//        productDTo.setProducers("wesam");
//        productRepository.save(productMapper.DTOToProduct(productDTo));
// }


    @Test
    public ResponseEntity<ProductDTO> testGet() throws URISyntaxException {

        ResponseEntity<ProductDTO> result = restTemplate.getForEntity("http://localhost:8081/api/v1/products/999", ProductDTO.class);
        /*
        Verify request succeed
         */
        Assert.assertEquals(200, result.getStatusCodeValue());
        System.out.println(result);
        return result;
    }

    @Test
    public void testPost() throws URISyntaxException {
        ProductDTO productDTo = new ProductDTO();
        productDTo.setOem("999");
        productDTo.setName("77");
        productDTo.setCostPrice(1500);
        productDTo.setSellingPrice(2000);
        productDTo.setCarType("DAF");
        productDTo.setQuantity(66);
        productDTo.setProducers("wesam");

        ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:8081/api/v1/products", productDTo, String.class);
        System.out.println(result);
        /*
         *   Verify request succeed
         */
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testDelete() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + localPort + "/api/v1/products/22";
        URI uri = new URI(baseUrl);
        restTemplate.delete(uri);
        testGet();
    }

    @Test
    public void testUpdate() throws URISyntaxException {

        ProductDTO productDTo = new ProductDTO();
        productDTo.setOem("999");
        productDTo.setName("77");
        productDTo.setCostPrice(1500);
        productDTo.setSellingPrice(2000);
        productDTo.setCarType("DAF");
        productDTo.setQuantity(66);
        productDTo.setProducers("wesam");
        final String baseUrl = "http://localhost:" + localPort + "/api/v1/products/22";
        URI uri = new URI(baseUrl);
        restTemplate.put(uri, productDTo);
        testGet();
    }
    /*
    159
     */

    @Test
    public void testGetAll() {

        ProductDTO productDTo = new ProductDTO();
        productDTo.setOem("22");
        productDTo.setName("AA");
        productDTo.setCostPrice(1500);
        productDTo.setSellingPrice(2000);
        productDTo.setCarType("DAF");
        productDTo.setQuantity(66);
        productDTo.setProducers("wesam");
        productRepository.save(productMapper.DTOToProduct(productDTo));
//dsfdsdfgf
        ProductDTO productDTo1 = new ProductDTO();
        productDTo1.setOem("122");
        productDTo1.setName("1A1A");
        productDTo1.setCostPrice(21500);
        productDTo1.setSellingPrice(20002550);
        productDTo1.setCarType("DAFtryutr");
        productDTo1.setQuantity(66545);
        productDTo1.setProducers("wesayyym");
        productRepository.save(productMapper.DTOToProduct(productDTo1));

        List<ProductDTO> dtoList = restTemplate.exchange("http://localhost:8081/api/v1/products/?page=1&pageSize=10",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDTO>>() {
                }).getBody();

        System.out.println(dtoList.get(1));

    }

}
