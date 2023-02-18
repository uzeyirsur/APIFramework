package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    static RequestSpecification reqSpec;

    public Utils() {
    }

    public RequestSpecification requestSpecification() throws IOException {


        if (reqSpec == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return reqSpec;
        }
        return reqSpec;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\omers\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
        properties.load(fis);
        return (String) properties.get(key);

    }

    public String getJsonPath(Response response, String key) {
        String responseString = response.asString();
        JsonPath js = new JsonPath(responseString);
        return js.get(key);
    }
}
