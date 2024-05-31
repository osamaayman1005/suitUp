package com.suitup.api;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestApiRequestExecutor {

    @Test
    public void testGetRequest(){
        EnvironmentConfiguration.setBaseUrl("https://api.github.com");
        ApiResult apiResult = ApiRequestExecutor.get("emojis");

        Assert.assertNotNull(apiResult);
    }
}
