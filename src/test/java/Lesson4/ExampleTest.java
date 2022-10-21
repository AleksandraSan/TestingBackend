package Lesson4;

import org.junit.jupiter.api.Test;

import static Lesson4.Homework.AbstractTest.getRequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ExampleTest extends AbstractTest{


    @Test
    void getRecipePositiveTest() {
        //используем реквест спецификацию, которую создаем сами в абстракном тесте
        given().spec(getRequestSpecification())
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .then()
                .spec(responseSpecification); //респонсе спецификация
    }


    @Test
    void getAccountInfoWithExternalEndpointTest(){
        Lesson4.Response response = given().spec(requestSpecification)
                .when()
                .formParam("title","Burger")//прописываем доп форм параметры
                .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
                .then()
                .extract()// важна эта последовательност чтобы вытащить из ответа body
                .response()
                .body() //сохраняем body в класс
                .as(Response.class); //
        assertThat(response.getCuisine(), containsString("American"));// проверяем что ответ содержит
    }

    @Test
    void test(){
        given().spec(requestSpecification)
                .when()
                .formParam("title","Burger")
                .formParam("language", "en")
                .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
                .then()
                .statusCode(200);
    }
}
