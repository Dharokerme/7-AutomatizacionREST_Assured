package co.com.sofka.stepdefinitions.login;

import co.com.sofka.stepdefinitions.setup.BaseSetUpServices;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTestStepDefinition extends BaseSetUpServices {

    private static final Logger LOGGER = Logger.getLogger(LoginTestStepDefinition.class);

    private Response response;
    private RequestSpecification request;

    @Given("el usuario esta en la pagina de inicio de sesion con el correo de usuario {string} y la contrasena {string}")
    public void elUsuarioEstaEnLaPaginaDeInicioDeSesionConElCorreoDeUsuarioYLaContrasena(String email, String contrasena) {
        try {
            generalSetUp();
            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(bodyStr(email, contrasena));

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    @When("el usuario presiona el boton de inicio de sesion")
    public void elUsuarioPresionaElBotonDeInicioDeSesion() {

        try {
            response = request.when().post(LOGIN_RESOURCE);

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

    @Then("el usuario deberia ver un codigo de repuesta exitoso y un token de respuesta")
    public void elUsuarioDeberiaVerUnCodigoDeRepuestaExitosoYUnTokenDeRespuesta() {

        try {
            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("token", notNullValue());

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }


    private String bodyStr(String email, String contrasena) {
        return "{\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + contrasena + "\"\n" +
                "}";
    }
}

