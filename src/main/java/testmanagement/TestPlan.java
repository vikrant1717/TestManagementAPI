package testmanagement;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
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

public class TestPlan {
    @Test
    @Description("This test attempts to create test Plan")
    @Severity(CRITICAL)
    @Owner("BrowserStack")
    public void createTestPlan(ITestContext context) throws IOException {
        String project_id = (String) context.getAttribute("projectID");

        String playload_Testplan = "\n" +
                "{\n" +
                "  \"name\": \"Data Migration\",\n" +
                "  \"description\": \"Rest assured \",\n" +
                "  \"start_date\": \"2023-12-30\",\n" +
                "  \"end_date\": \"2023-12-31\"\n" +
                "}\n";
        Response responsePlan = given(getRequestSpec()).
                contentType("application/json").
                body(playload_Testplan).
                when().
                post("/projects/" + project_id + "/test-plans").
                then().spec(getResponseSpec()).
                extract().response();
        String testplan_id = responsePlan.jsonPath().getString("test_plan.identifier");

        System.out.println("Identifier value is : " + testplan_id);

        System.out.println(" response is ----------------" + responsePlan.getBody().asString());
        assertThat("expected status code is incorrect",
                responsePlan.statusCode(), equalTo(200));

        context.setAttribute("testplanID", testplan_id);

        System.out.println("TestPlan id is "+ testplan_id);
    }

    @Test
    @Description("This test attempts to get test runs linked to test plan")
    @Severity(CRITICAL)
    @Owner("BrowserStack")
    public void getTestRunsLinkedToTestPlan(ITestContext context) throws IOException {
        String project_id = (String) context.getAttribute("projectID");
        String testplan_id = (String) context.getAttribute("testplanID");
        given(getRequestSpec()).
                when().
                get("/projects/" + project_id + "/test-plans/" + testplan_id + "/test-runs").
                then().spec(getResponseSpec()).
                log().all().
                extract().response();
    }
}
