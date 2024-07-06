package com.suitup.api;

import com.suitup.api.configuration.EnvironmentConfiguration;
import com.suitup.api.curl.CurlParser;
import com.suitup.api.model.ApiRequest;
import com.suitup.api.model.ApiResult;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SuitUpTests {

    @Test
    public void testGetRequest(){
        EnvironmentConfiguration.setBaseUrl("https://api.github.com");
        ApiResult apiResult = ApiRequestExecutor.get("emojis");
        System.out.println(apiResult.getCurl());
        Assert.assertNotNull(apiResult);
    }
    @Test
    public void testCurlParserForGetRequests(){
        String curl = "curl --location 'https://api.github.com/emojis'";
        ApiRequest request = CurlParser.parseCurl(curl);
        System.out.println(request);
    }

    @Test
    public void testCurlParserForPostRequests(){
        String curl = "curl --location 'https://backend.staging.enable.tech/integration/order_payment' \\\n" +
                "--header 'saasapikey: PSM993X-YZCMVEH-K2FF6DE-TJCV3VK' \\\n" +
                "--header 'Content-Type: application/json' \\\n" +
                "--data-raw '{\n" +
                "    \"integrationType\": \"order\",\n" +
                "    \"orderData\": {\n" +
                "        \"orderType\": \"restaurant\",\n" +
                "        \"first_name\": \"aam\",\n" +
                "        \"last_name\": \"samir\",\n" +
                "        \"phone\": \"54545454\",\n" +
                "        \"country_code\": \"974\",\n" +
                "        \"email\": \"none@none.none\",\n" +
                "        \"pickup_date\": \"2024-05-15\",\n" +
                "        \"pickup_time\": \"19:44\",\n" +
                "        \"delivery_date\": \"2024-05-15\",\n" +
                "        \"delivery_time\": \"20:00\",\n" +
                "        \"invoiced_amount\": 25,\n" +
                "        \"delivery_amount\": 1,\n" +
                "        \"total_amount\": 26,\n" +
                "        \"order_remarks\": \"\",\n" +
                "        \"payment_method\": \"cash\",\n" +
                "        \"source\": \"webstore\",\n" +
                "        \"delivery_action\": \"delivery_location\",\n" +
                "        \"discount\": {\n" +
                "            \"$numberInt\": \"0\"\n" +
                "        },\n" +
                "        \"delivery_type\": \"urgent\",\n" +
                "        \"delivery_slot_from\": \"20:00\",\n" +
                "        \"delivery_slot_to\": \"21:00\",\n" +
                "        \"items\": [\n" +
                "            {\n" +
                "                \"index\": 0,\n" +
                "                \"name\": \"Churros \",\n" +
                "                \"name_ar\": \"Churros \",\n" +
                "                \"plu\": \"1021\",\n" +
                "                \"price\": 25,\n" +
                "                \"quantity\": 1,\n" +
                "                \"discount\": 0,\n" +
                "                \"totalAmount\": 25,\n" +
                "                \"totalAmountAfterDiscount\": 25,\n" +
                "                \"mnodifierGroups\": [],\n" +
                "                \"subProducts\": [],\n" +
                "                \"itemReference\": \"1021\",\n" +
                "                \"special_instructions\": \"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"is_test\": false,\n" +
                "        \"enable_branch_id\": \"\",\n" +
                "        \"language\": \"english\",\n" +
                "        \"unknown_gift_recipient_location\": false,\n" +
                "        \"deliveryLocation\": {\n" +
                "            \"type\": \"PIN_LOCATION\",\n" +
                "            \"addressType\": \"HOUSE\",\n" +
                "            \"nickname\": \"Home\",\n" +
                "            \"country\": \"Qatar\",\n" +
                "            \"buildingName\": \"t12\",\n" +
                "            \"area\": \"Al Wajba\",\n" +
                "            \"city\": \"Al Wajba\",\n" +
                "            \"pinLink\": \"https://maps.google.com/?q=25.290801435020178,51.38571676605478\",\n" +
                "            \"latitude\": \"25.290801435020178\",\n" +
                "            \"longitude\": \"51.38571676605478\",\n" +
                "            \"additionalInfo\": \"\",\n" +
                "            \"nearestLandmark\": \"\"\n" +
                "        }\n" +
                "    }\n" +
                "}'";
        ApiRequest request = CurlParser.parseCurl(curl);
        Assert.assertNotNull(request);
        ApiResult apiResult = ApiRequestExecutor.execute(request);
        System.out.println(apiResult.getCurl());
    }


    @Test
    public void testExecuteApiRequest(){
        String curl = "curl 'https://backend.testing.enable.tech/v2/coupon?companyId=646c8b9032701138ae5cd634' \\\n" +
                "  -H 'Accept: application/json' \\\n" +
                "  -H 'Accept-Language: en-US,en;q=0.9' \\\n" +
                "  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0NmM4YjkwMzI3MDExMzhhZTVjZDYzNiIsImZvciI6InVzZXIiLCJpYXQiOjE3MTczMzA3MjksImV4cCI6MTcxOTkyMjcyOX0.vs0fWgWt9SIq6yqZKANqKu2FBbCV1NvhthHxYc1RKv4' \\\n" +
                "  -H 'Connection: keep-alive' \\\n" +
                "  -H 'Origin: https://enable-mfs.testing.enable.tech' \\\n" +
                "  -H 'Referer: https://enable-mfs.testing.enable.tech/' \\\n" +
                "  -H 'Sec-Fetch-Dest: empty' \\\n" +
                "  -H 'Sec-Fetch-Mode: cors' \\\n" +
                "  -H 'Sec-Fetch-Site: same-site' \\\n" +
                "  -H 'Sec-GPC: 1' \\\n" +
                "  -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36' \\\n" +
                "  -H 'sec-ch-ua: \"Brave\";v=\"125\", \"Chromium\";v=\"125\", \"Not.A/Brand\";v=\"24\"' \\\n" +
                "  -H 'sec-ch-ua-mobile: ?0' \\\n" +
                "  -H 'sec-ch-ua-platform: \"Windows\"' \\\n" +
                "  -H 'x-access-token: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0NmM4YjkwMzI3MDExMzhhZTVjZDYzNiIsImZvciI6InVzZXIiLCJpYXQiOjE3MTczMzA3MjksImV4cCI6MTcxOTkyMjcyOX0.vs0fWgWt9SIq6yqZKANqKu2FBbCV1NvhthHxYc1RKv4'";
        ApiRequest request = CurlParser.parseCurl(curl);
        ApiResult result = ApiRequestExecutor.execute(request);
        Assert.assertNotNull(result);
    }

}
