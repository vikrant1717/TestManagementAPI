package api;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;
import java.util.Base64;


import static io.restassured.RestAssured.basic;
import static utilities.Loader.configPropertiesLoader;
import static utilities.PropertyReader.configProperties;

public class CommonSpecs {

    public static RequestSpecification getRequestSpec() throws IOException {

//        String username = "vikrant_oLsTOA";
//        String password = "2U4dGs4SZjNdJGBTZi7A";
//
//        String token11= new String(Base64.getEncoder().encode((username + ":" + password).getBytes()));
//
//        System.out.println("converted tokken is " + token11) ;


        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        return new RequestSpecBuilder().
                setBaseUri("https://test-management.browserstack.com").
                setBasePath("/api/v2").

                addHeader("Authorization", "Basic " + configProperties("token")).
                     //   addHeader("Authorization", "Basic " + token11).
                // addHeader("Authorization", "Basic " + configPropertiesLoader("toekn")).
                log(LogDetail.ALL).build();
    }
    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().build();
               // expectStatusCode(200).build();
        //  log(LogDetail.ALL).build();
    }

}
