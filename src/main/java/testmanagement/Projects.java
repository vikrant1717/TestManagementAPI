package testmanagement;


import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import org.apache.commons.cli.*;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

import static api.CommonSpecs.getRequestSpec;
import static api.CommonSpecs.getResponseSpec;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Projects {

    @Test
    @Description("This test attempts to get Project id")
    @Severity(CRITICAL)
    @Owner("BrowserStack")
    public static void getProjectid(ITestContext context) throws IOException {
        String project_id = given(getRequestSpec()).
                when().
                get("/projects").
                then().spec(getResponseSpec()).
                log().headers().
               // log().all().
                extract().response().
                jsonPath().getString("projects[0].identifier");
        System.out.println("the project id is " + project_id);
        context.setAttribute("projectID", project_id);
    }

}
