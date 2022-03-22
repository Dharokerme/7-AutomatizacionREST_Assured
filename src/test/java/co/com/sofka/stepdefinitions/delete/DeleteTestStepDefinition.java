package co.com.sofka.stepdefinitions.delete;

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

public class DeleteTestStepDefinition extends BaseSetUpServices {

    private static final Logger LOGGER = Logger.getLogger(DeleteTestStepDefinition.class);

    private Response response;
    private RequestSpecification request;

    @Given("el administrador esta en la pagina de borrar")
    public void elAdministradorEstaEnLaPaginaDeBorrar() {
        try {
            generalSetUp();
            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON);

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }

    }
    @When("el administrador presiona el boton borrar pagina")
    public void elAdministradorPresionaElBotonBorrarPagina() {
        try {
            response = request.when().delete(DELETE_RESOURCE);

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }

    }
    @Then("el administrador deberia ver un codigo de repuesta exitoso \\({int} sin contenido).")
    public void elAdministradorDeberiaVerUnCodigoDeRepuestaExitosoSinContenido(Integer int1) {
        try {
            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_NO_CONTENT);

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }
    }

}
