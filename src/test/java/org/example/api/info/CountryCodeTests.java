package org.example.api.info;

import io.restassured.response.Response;
import org.example.api.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.api.Utils.findJsonNode;
import static org.example.api.info.InfoApi.getInfoRequest;

public class CountryCodeTests extends BaseTest {

    @Test
    public void checkCountryCodesTest() {
        Response response = getInfoRequest("IND", "ATUSD");
        String responseBody = response.getBody().asString();

        List<String> countryCodes = findJsonNode(responseBody, "country_code");

        for (String countryCode : countryCodes) {
            assertThat(countryCode).isEqualTo("IND");
        }
    }

    @Test
    public void checkCountryCodesNegativeTest() {
        Response response = getInfoRequest("WRONG_COUNTRY_CODE", "ATUSD");
        String responseBody = response.getBody().asString();
        List<String> countryCodes = findJsonNode(responseBody, "country_code");

        assertThat(countryCodes.isEmpty()).isTrue();
    }
}
