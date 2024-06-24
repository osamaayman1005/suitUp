
# Suit up 
a low code test automation library
(currently supporting REST API testing only)


## Installation

Install the library by adding the following code into your pom.xml

```xml
    <dependencies>
        <!-- Other dependencies -->
        <dependency>
            <groupId>com.github.osamaayman1005</groupId>
            <artifactId>suitUp</artifactId>
            <version>0.3.0</version>
        </dependency>
    </dependencies>
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
```
    
## Usage
you can use post, get, put, patch and delete requests by statically call any of them from ApiRequestExecutor class 

Example:
```java
public class LoginApi {
    public static ApiResult login(String email, String password){
        return ApiRequestExecutor.post("auth/login", new HashMap<>(){{
            put("email", email);
            put("password", password);
        }});
    }
}
```
you can also parse a curl using CurlParser.parseCurl method and then change any of the request elements if needed then run it.

Example:
```java
    @Test
    public void testFromCurl(){
        String curl = "curl --location 'https://api.github.com/emojis'";
        ApiRequest request = CurlParser.parseCurl(curl);
        ApiResult result = ApiRequestExecutor.execute(request);
        //your assertions
    }
```

For Global configuration, you can use:

EnvironmentConfiguration.setBaseUrl() to set the baseUrl for all your requests (excluding requests read from curl)

AuthenticationConfiguration.addAuthenticationToken() to add your custom api auth

AuthenticationConfiguration.addBearerAuthenticationToken() to add your bearer token

you will find the response elements in ApiResult + the curl of the request in case you want to copy it or use it to report a failure to the developer

you won't need to include testng as it is part of the library
