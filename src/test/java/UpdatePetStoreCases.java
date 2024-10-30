import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UpdatePetStoreCases {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void testUpdatePet_Positive() {
        String updatedPetJson = "{ \"id\": 1, \"name\": \"Updated Dog\", \"status\": \"was adopted\" }";

        given()
                .contentType(ContentType.JSON)
                .body(updatedPetJson)
                .when()
                .put(BASE_URL + "/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated Dog"))
                .body("status", equalTo("was adopted")).log().all();
    }

    @Test
    public void testUpdatePet_Invalid_Id() {
        String invalidPetJson = "{ \"id\": *, \"status\": \"\" }";

        given()
                .contentType(ContentType.JSON)
                .body(invalidPetJson)
                .when()
                .put(BASE_URL + "/pet")
                .then()
                .statusCode(400).log().all();  //Invalid ID supplied
    }

    @Test
    public void testUpdatePet_Negative_PetNotFound() {
        String invalidPetJson = "{ \"id\": -1997544, \"status\": \"xxx\" }";

        given()
                .contentType(ContentType.JSON)
                .body(invalidPetJson)
                .when()
                .put(BASE_URL + "/pe")
                .then()
                .statusCode(404).log().all();  // Pet Not Found
    }

    @Test
    public void testUpdatePet_Negative_ValidationException() {
        String invalidPetJson = "{ \"id\": -1997544, \"status\": \"xxx\" }";

        given()
                .contentType(ContentType.JSON)
                .body(invalidPetJson)
                .when()
                .delete(BASE_URL + "/pet")
                .then()
                .statusCode(405).log().all();  // Validation Exception
    }

}
