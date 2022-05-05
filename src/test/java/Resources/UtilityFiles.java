package Resources;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilityFiles {

public static String getGlobalPropertiesData(String key) throws IOException {

    Properties prop = new Properties();
    FileInputStream fis = new FileInputStream("C:\\Users\\Sharath\\IdeaProjects\\CucumberRestAPIE2E\\src\\test\\java\\Resources\\data.properties");
    prop.load(fis);

    return prop.getProperty(key);


}

public static String getJSONPathValue(Response response, String keyValue){

    String resp = response.asString();
    JsonPath jp = new JsonPath(resp);
    return jp.get(keyValue).toString();
}
}
