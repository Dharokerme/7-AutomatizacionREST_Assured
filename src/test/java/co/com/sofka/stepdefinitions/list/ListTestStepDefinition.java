package co.com.sofka.stepdefinitions.list;

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
import static org.hamcrest.Matchers.hasSize;

public class ListTestStepDefinition extends BaseSetUpServices {

    private static final Logger LOGGER = Logger.getLogger(ListTestStepDefinition.class);
    private Response response;
    private RequestSpecification request;

    @Given("el administrador esta en la pagina de inicio")
    public void elAdministradorEstaEnLaPaginaDeInicio() {
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

    @When("el administrador hace click en el boton de lista de usuarios")
    public void elAdministradorHaceClickEnElBotonDeListaDeUsuarios() {
        try {
            response = request.when().get(LIST_RESOURCE);

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());
        }

    }

    @Then("el administrador vera una lista de usuarios")
    public void elAdministradorVeraUnaListaDeUsuarios() {

        try {
            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_OK)
                    //assertThat the data list has the correct size
                    .body("data", hasSize(6));

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            Assertions.fail(exception.getMessage());

        }
    }
}
