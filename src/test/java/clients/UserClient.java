package clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    private RequestSpecification getRequestSpec() {
        return given()
                .header("Content-type", "application/json")
                .relaxedHTTPSValidation() // Отключаем проверку SSL
                .log().all();
    }

    public Response createUser(User user) {
        return getRequestSpec()
                .body("{\"email\":\"" + user.getEmail() + "\",\"password\":\"" + user.getPassword() + "\",\"name\":\"" + user.getName() + "\"}")
                .when()
                .post(BASE_URL + "/auth/register");
    }

    public Response loginUser(User user) {
        return getRequestSpec()
                .body("{\"email\":\"" + user.getEmail() + "\",\"password\":\"" + user.getPassword() + "\"}")
                .when()
                .post(BASE_URL + "/auth/login");
    }

    public Response deleteUser(String accessToken) {
        if (accessToken != null && !accessToken.isEmpty()) {
            return getRequestSpec()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(BASE_URL + "/auth/user");
        }
        return null;
    }

    public String getAccessToken(User user) {
        try {
            Response response = loginUser(user);
            if (response.statusCode() == 200) {
                return response.then().extract().path("accessToken");
            }
        } catch (Exception e) {
            System.out.println("Error getting access token: " + e.getMessage());
        }
        return null;
    }
}