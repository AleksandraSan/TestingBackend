package Lesson5;

import lesson5.api.ProductService;
import lesson5.dto.GetCategoryResponse;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductByIdTest {

    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @Test
    void getProductByIdTest() throws IOException {
        Response<Product> response = productService.getProductById(1112).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1112));
        assertThat(response.body().getTitle(), equalTo("Egg"));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
    }
}
