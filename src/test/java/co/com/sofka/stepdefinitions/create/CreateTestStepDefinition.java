package co.com.sofka.stepdefinitions.create;

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
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class CreateTestStepDefinition extends BaseSetUpServices {

    private static final Logger LOGGER = Logger.getLogger(CreateTestStepDefinition.class);

    private Response response;
    private RequestSpecification request;

    @Given("el trabajador va a crear un usuario {string} con puesto {string}")
    public void elClienteVaACrearUnUsuario(String name, String job) {
        try {
            generalSetUp();
            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(bodyStrlog(name, job));


        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }

    }

    @When("el trabajador presiona el boton crear usuario")
    public void elClientePresionaElBotonCrearUsuario() {
        try {
            response = request.when().post(POST_RESOURCE);

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }

    }

    @Then("el trabajador deberia ver un codigo de repuesta exitoso \\({int} creado) y su informacion.")
    public void elClienteDeberiaVerUnCodigoDeRepuestaExitosoCreadoYSuInformacion(Integer int1) {
        try {
            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .body("id", is(notNullValue()));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }

    }

    private String bodyStrlog(String name, String job) {
        return "{\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"job\": \"" + job + "\"\n" +
                "}";
    }
}