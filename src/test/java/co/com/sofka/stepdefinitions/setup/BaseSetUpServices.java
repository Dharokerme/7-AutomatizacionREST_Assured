package co.com.sofka.stepdefinitions.setup;
import io.restassured.RestAssured;
import org.apache.log4j.PropertyConfigurator;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;

public class BaseSetUpServices {
    private static final String BASE_URI = "https://reqres.in";
    private static final String BASE_PATH = "/api";
    protected static final String LOGIN_RESOURCE = "/login";
    protected static final String DELETE_RESOURCE = "/users/2";
    protected static final String POST_RESOURCE = "/users";
    protected static final String LIST_RESOURCE = "/users?page=2";

    protected void generalSetUp() {
        setUpLog4j2();
        setUpBases();
    }

    private void setUpLog4j2() {
        PropertyConfigurator.configure( System.getProperty("user.dir") + "/" + LOG4J_PROPERTIES_FILE_PATH.getValue());
    }

    private void setUpBases() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }


}

