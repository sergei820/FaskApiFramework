package org.example.api.info;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.api.Utils.getNestedElementsList;
import static org.example.api.info.InfoApi.getInfoRequest;

public class AssetTests {

    @Test
    public void checkReceiveAssets() {
        Response response = getInfoRequest("WWC", "null");
        String responseBody = response.getBody().asString();

        List<String> assetList = getNestedElementsList(responseBody, "receive");

        for (String asset : assetList) {
            assertThat(asset.contains("AT")).as("Looks like there's a wrong asset type: " + asset).isTrue();
        }
    }
}
