import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeletePetStoreCases {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";


    @Test
    public void testDeletePet_Positive() {
        int petIdToDelete = 1;

        given()
                .when()
                .delete(BASE_URL + "/pet/" + petIdToDelete)
                .then()
                .statusCode(200).log().all();
    }

    @Test
    public void testDeletePet_Negative_PetNotFound() {
        int petId = 9999;

        given()
                .when()
                .delete(BASE_URL + "/pet" + petId)
                .then()
                .statusCode(404).log().all();  // Pet Not Found
    }
}

