package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
//import org.apache.poi.xdgf.util.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class specBuilders extends UtilityFiles {

    public static RequestSpecification req;

    public RequestSpecification requestSpecBuilder() throws IOException {

        if(req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder()
                    .setBaseUri(getGlobalPropertiesData("baseURL"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .build();

            return req;
        }
        return req;

    }
}
