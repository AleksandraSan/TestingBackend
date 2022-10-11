import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class MealPlanningTest extends AbstractTest{

    @Test
    public void connectUser(){
        String hash = given()
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"username\":" + getUsername()+",\n"
                        + " \"firstname\":"+ getFirstname() +",\n"
                        + " \"lastname\": "+ getLastname() +",\n"
                        + " \"email\":"+ getEmail() +",\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/users/connect")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("hash")
                .toString();

        System.out.println(hash);


        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", hash)
                .queryParam("username" , getUsername())
                .pathParam("username", getUsername())
                .header("Content-Type", "application/json")
                .when()
                .get("https://api.spoonacular.com/mealplanner/{username}/templates")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK")
                .time(lessThan(2000L));

        String id = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", hash)
                .header("Content-Type", "application/json")
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/alex_cool_user/items")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        System.out.println(id);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", hash)
                .when()
                .post("https://api.spoonacular.com/mealplanner/alex_cool_user/items"+ id)
                .then()
                .assertThat()
                .statusCode(200);
    }
}
