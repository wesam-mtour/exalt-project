package com.exalt.sparepartsmanagement.tests;

import com.exalt.sparepartsmanagement.dto.ProductDTO;
import com.exalt.sparepartsmanagement.mapper.ProductMapper;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {

    @LocalServerPort
    int localPort;

    private Logger logger = LoggerFactory.getLogger(ProductServiceTest.class);

    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    private TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    @Order(1)
    public void testGet() {
        ProductDTO productDTO = createRandomProduct();
        postProduct(productDTO);

        ResponseEntity<ProductDTO> result = restTemplate.getForEntity("http://localhost:" + localPort + "/api/v1/products/" + productDTO.getOem(),
                ProductDTO.class);
        /*
        Verify request succeed
         */
        Assert.assertEquals(200, result.getStatusCodeValue());
        assertAll(
                () -> assertEquals(result.getBody().getOem(), productDTO.getOem()),
                () -> assertEquals(result.getBody().getName(), productDTO.getName()),
                () -> assertEquals(result.getBody().getCarType(), productDTO.getCarType()),
                () -> assertEquals(result.getBody().getProducers(), productDTO.getProducers()),
                () -> assertEquals(result.getBody().getCostPrice(), productDTO.getCostPrice()),
                () -> assertEquals(result.getBody().getSellingPrice(), productDTO.getSellingPrice()),
                () -> assertEquals(result.getBody().getQuantity(), productDTO.getQuantity())
        );
        logger.info(result.getBody().toString());
    }

    @Test
    @Order(2)
    public void testDelete() throws URISyntaxException {
        ProductDTO productDTO = createRandomProduct();
        postProduct(productDTO);

        final String baseUrl = "http://localhost:" + localPort + "/api/v1/products/" + productDTO.getOem();
        URI uri = new URI(baseUrl);
        restTemplate.delete(uri);
        /*
         *   Verify request succeed
         */
        ResponseEntity<ProductDTO> result = restTemplate.getForEntity("http://localhost:" + localPort + "/api/v1/products/" + productDTO.getOem(), ProductDTO.class);
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(3)
    public void testPost() throws URISyntaxException {
        ProductDTO productDTO = createRandomProduct();
        ResponseEntity<ProductDTO> result = restTemplate.postForEntity("http://localhost:" + localPort + "/api/v1/products/", productDTO, ProductDTO.class);
        /*
         *   Verify request succeed
         */
        Assert.assertEquals(200, result.getStatusCodeValue());
        assertAll(
                () -> assertEquals(result.getBody().getOem(), productDTO.getOem()),
                () -> assertEquals(result.getBody().getName(), productDTO.getName()),
                () -> assertEquals(result.getBody().getCarType(), productDTO.getCarType()),
                () -> assertEquals(result.getBody().getProducers(), productDTO.getProducers()),
                () -> assertEquals(result.getBody().getCostPrice(), productDTO.getCostPrice()),
                () -> assertEquals(result.getBody().getSellingPrice(), productDTO.getSellingPrice()),
                () -> assertEquals(result.getBody().getQuantity(), productDTO.getQuantity())
        );
        logger.info(result.getBody().toString());
    }

    @Test
    @Order(4)
    public void testUpdate() throws URISyntaxException {
        ProductDTO productDTO = createRandomProduct();
        postProduct(productDTO);

        final String baseUrl = "http://localhost:" + localPort + "/api/v1/products/" + productDTO.getOem();
        URI uri = new URI(baseUrl);
        /*
        updating product...
         */
        productDTO.setOem("999");
        productDTO.setName("77");
        productDTO.setCostPrice(5000);
        productDTO.setSellingPrice(4000);
        productDTO.setCarType("VOLVO");
        productDTO.setQuantity(100);
        productDTO.setProducers("mohammad");

        restTemplate.put(uri, productDTO);
        /*
         *   Verify request succeed
         */
        ResponseEntity<ProductDTO> result = restTemplate.getForEntity("http://localhost:" + localPort + "/api/v1/products/" + productDTO.getOem(), ProductDTO.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        assertAll(
                () -> assertEquals(result.getBody().getOem(), productDTO.getOem()),
                () -> assertEquals(result.getBody().getName(), productDTO.getName()),
                () -> assertEquals(result.getBody().getCarType(), productDTO.getCarType()),
                () -> assertEquals(result.getBody().getProducers(), productDTO.getProducers()),
                () -> assertEquals(result.getBody().getCostPrice(), productDTO.getCostPrice()),
                () -> assertEquals(result.getBody().getSellingPrice(), productDTO.getSellingPrice()),
                () -> assertEquals(result.getBody().getQuantity(), productDTO.getQuantity())
        );
        logger.info(result.getBody().toString());
    }

    @Test
    @Order(5)
    public void testGetAll() {

        ProductDTO productDTo = new ProductDTO();
        productDTo.setOem("22");
        productDTo.setName("AA");
        productDTo.setCostPrice(1500);
        productDTo.setSellingPrice(2000);
        productDTo.setCarType("DAF");
        productDTo.setQuantity(66);
        productDTo.setProducers("wesam");
        ResponseEntity<ProductDTO> result = restTemplate.postForEntity("http://localhost:" + localPort + "/api/v1/products/", productDTo, ProductDTO.class);

        ProductDTO productDTo1 = new ProductDTO();
        productDTo1.setOem("122");
        productDTo1.setName("1A1A");
        productDTo1.setCostPrice(21500);
        productDTo1.setSellingPrice(20002550);
        productDTo1.setCarType("DAFtryutr");
        productDTo1.setQuantity(66545);
        productDTo1.setProducers("wesayyym");
        result = restTemplate.postForEntity("http://localhost:" + localPort + "/api/v1/products/", productDTo1, ProductDTO.class);

        List<ProductDTO> dtoList = restTemplate.exchange("http://localhost:" + localPort + "/api/v1/products/" + "?page=1&pageSize=10",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDTO>>() {
                }).getBody();

        logger.info(dtoList.get(1).toString());
    }

    public ProductDTO createRandomProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setOem("22");
        productDTO.setName("AA");
        productDTO.setCostPrice(1500);
        productDTO.setSellingPrice(2000);
        productDTO.setCarType("DAF");
        productDTO.setQuantity(66);
        productDTO.setProducers("wesam");
        return productDTO;
    }

    public void postProduct(ProductDTO productDTO) {
        ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:" + localPort + "/api/v1/products/", productDTO, String.class);
    }
}
