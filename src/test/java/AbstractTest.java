import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractTest {
    static Properties property = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;
    private static String query;
    private static String diet;
    private static String includeIngredients;
    private static String excludeIngredients;
    private static String titleMatch;
    private static String title;
    private static String ingredientList;
    private static String username;
    private static String firstname;
    private static String email;
    private static String lastname;


    @BeforeAll
    static void initTest () throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties");
        property.load(configFile);

        apiKey = property.getProperty("apiKey");
        baseUrl = property.getProperty("base_url");
        query = property.getProperty("query");
        diet = property.getProperty("diet");
        includeIngredients = property.getProperty("includeIngredients");
        excludeIngredients = property.getProperty("excludeIngredients");
        titleMatch = property.getProperty("titleMatch");
        title = property.getProperty("title");
        ingredientList = property.getProperty("ingredientList");
        username = property.getProperty("username");
        firstname = property.getProperty("firstname");
        lastname = property.getProperty("lastname");
        email = property.getProperty("email");
    }

    public static String getFirstname() {
        return firstname;
    }

    public static String getEmail() {
        return email;
    }

    public static String getUsername() {
        return username;
    }

    public static String getLastname() {
        return lastname;
    }

    public static String getIngredientList() {
        return ingredientList;
    }

    public static String getTitle() {
        return title;
    }

    public static String getTitleMatch() {
        return titleMatch;
    }

    public static String getExcludeIngredients() {
        return excludeIngredients;
    }

    public static String getIncludeIngredients() {
        return includeIngredients;
    }

    public static String getDiet() {
        return diet;
    }

    public static String getQuery() {
        return query;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getApiKey() {
        return apiKey;
    }
}
