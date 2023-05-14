package org.example.api.info;

import io.restassured.response.Response;
import org.example.api.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.api.info.InfoApi.getInfoRequest;

public class StatusCodeTests extends BaseTest {

    @Test
    public void testStatusCodeWithNoParams() {
        Response response = getInfoRequest(null, null);
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void testStatusCodeWithCountriesParam() {
        Response response = getInfoRequest("WWC", null);
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void testStatusCodeWithAssetParam() {
        Response response = getInfoRequest(null, "ATUSD");
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void testStatusCodeWithAllParams() {
        Response response = getInfoRequest("WWC", "ATUSD");
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void testStatusCodeWithWrongParamValues() {
        Response response = getInfoRequest("WWCC", "ATUSDD");
        assertThat(response.getStatusCode()).isEqualTo(200);
    }
}
