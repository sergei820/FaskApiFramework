package org.example.api.info;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.example.api.BaseTest.baseUrl;

public class InfoApi {

    public static String infoEndpoint = "/api/v2/payments/edeab824-178e-4fd7-9bf0-bd88a6fd114a/sep0031/info";

    public static Response getInfoRequest(String countries, String asset) {
        RequestSpecification requestSpec = RestAssured.given();
        requestSpec.baseUri(baseUrl + infoEndpoint);
        if (countries != null) {
            requestSpec.queryParam("countries", countries);
        }
        if (asset != null) {
            requestSpec.queryParam("asset", asset);
        }

        return requestSpec.get();
    }
}
