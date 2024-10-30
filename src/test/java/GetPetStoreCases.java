import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetPetStoreCases {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void testGetPetById_Positive() {
        int petId = 1;

        given()
                .when()
                .get(BASE_URL + "/pet/" + petId)
                .then()
                .statusCode(200)
                .body("id", equalTo(petId)).log().all();
    }

    @Test
    public void testGetPetById_Negative_PetNotFound() {
        int nonExistentPetId = 9999;

        given()
                .when()
                .get(BASE_URL + "/pet/" + nonExistentPetId)
                .then()
                .statusCode(404).log().all();  //Pet not found
    }
}
