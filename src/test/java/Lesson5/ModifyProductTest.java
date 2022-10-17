package Lesson5;

import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

public class ModifyProductTest {

    static ProductService productService;
    Product product = null;
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withId(1114)
                .withTitle("Cocos")
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }

    @Test
    @DisplayName("Изменение (модифицировании) информации о продукте")
    void ModificationProductTest() throws IOException {
        Response<Product> response = productService.modifyProduct(product)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
