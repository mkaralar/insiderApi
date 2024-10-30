import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostPetStoreCases {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void testCreatePet_Positive() {
        String petJson = "{ \"id\": 1, \"name\": \"Dog\", \"status\": \"available\" }";

        given()
                .contentType(ContentType.JSON)
                .body(petJson)
                .when()
                .post(BASE_URL + "/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("Dog"))
                .body("status", equalTo("available")).log().all();
    }

    @Test
    public void testCreatePet_Negative_MissingId() {
        String invalidPetJson = "{ \"id\": , \"name\": \"\", \"status\": \"available\" }";

        given()
                .contentType(ContentType.JSON)
                .body(invalidPetJson)
                .when()
                .post(BASE_URL + "/pet")
                .then()
                .statusCode(400).log().all(); // Missing Id
    }

    @Test
    public void testCreatePet_Negative_InvalidInput() {
        String invalidPetJson = "{ \"id\": , \"name\": \"\", \"status\": \"available\" }";

        given()
                .contentType(ContentType.JSON)
                .body(invalidPetJson)
                .when()
                .delete(BASE_URL + "/pet")
                .then()
                .statusCode(405).log().all(); //Invalid Input
    }
}

