package testmanagement;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;

import static api.CommonSpecs.getRequestSpec;
import static api.CommonSpecs.getResponseSpec;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestRuns {

    @Test
    @Description("This test attempts to get test run list")
    @Severity(CRITICAL)
    @Owner("BrowserStack")
    public void getTestRunList(ITestContext context) throws IOException {
        String project_id = (String) context.getAttribute("projectID");

        Response res = given(getRequestSpec()).
                log().cookies().
                when().
                get("/projects/" + project_id + "/test-runs").
                then().spec(getResponseSpec()).
                extract().response();
        String testrun_id = res.jsonPath().getString("test_runs[0].identifier");
        System.out.println("Identifier value is : " + testrun_id);
        assertThat("Incorrect status code", res.getStatusCode(), equalTo(200));
        System.out.println("Response of getTestRunList is " + res);
        //context.setAttribute("testrunsID", testrun_id);

    }


        @Test
    @Description("This test attempts to create test runs")
    @Severity(CRITICAL)
    @Owner("BrowserStack")
    public void createTestRuns(ITestContext context) throws IOException {
        String project_id = (String) context.getAttribute("projectID");

        String payload = "{\n" +
                "  \"test_run\": {\n" +
                "    \"name\": \"Test Run-23/1/2024\",\n" +
                "    \"description\": \"check the performance of the test run\",\n" +
                "    \"run_state\": \"new_run\",\n" +
                "    \"owner\": \"xyz@email.com\",\n" +
                "    \"test_case_owner\": \"xyz@email.com\",\n" +
                "    \"tags\": [\n" +
                "      \"Regression\"\n" +
                "    ],\n" +
                "    \"issues\": [\n" +
                "      \"TASK-309\"\n" +
                "    ],\n" +
                "    \"configurations\": [],\n" +
                "    \"test_plan_id\" : \"TP-1\",\n" +
                "    \"test_cases\": [\"TC-1\", \"TC-3\"],\n" +
                "    \"folder_ids\": [34, 54, 64],\n" +
                "    \"include_all\": true\n" +
                "  }\n" +
                "}\n" +
                "\n";
        Response ctr = given(getRequestSpec()).
                contentType("application/json").
                log().cookies().
                body(payload).
                when().
                post("/projects/" + project_id + "/test-runs").
                then().spec(getResponseSpec()).
                extract().response();
        String testrun_id = ctr.jsonPath().getString("testrun.identifier");
        assertThat("expected status code is incorrect", ctr.statusCode(), equalTo(200));
        context.setAttribute("testrunsID", testrun_id);
        //System.out.println("Response of method createTestRuns is : " + ctr.asPrettyString());

    }

    @Test
    @Description("This test attempts close test runs")
    @Severity(CRITICAL)
    @Owner("BrowserStack")
    public void closeTestRuns(ITestContext context) throws IOException {
        String testrun_id = (String) context.getAttribute("testrunsID");
        String project_id = (String) context.getAttribute("projectID");
        Response responseTestRun = given(getRequestSpec()).
                contentType("application/json").
                when().
                post("/projects/" + project_id + "/test-runs/" + testrun_id + "/close").
                then().spec(getResponseSpec()).
                extract().response();
        System.out.println(" response is ----------------" + responseTestRun.getBody().asString());
        assertThat("expected status code is incorrect",
                responseTestRun.statusCode(), equalTo(200));
    }

}
