package Resources;

import java.util.Arrays;
import java.util.HashMap;

public class testDataBuild {

    public static HashMap<String, Object> hashMapJsonBuild(String name,String address,String website) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("accuracy", 20);
        map.put("name", name);
        map.put("phone_number", "123456789");
        map.put("address", address);
        map.put("website", website);
        HashMap<String, Object> locationMap = new HashMap<>();
        locationMap.put("lat", -38.383494);
        locationMap.put("lng", 33.427362);
        map.put("location", locationMap);
        map.put("types", Arrays.asList("Type1", "Type2"));

        return map;


    }

    public static String deletePlaceAPI(String placeID){

        return "{\n" +
                "    \"place_id\":\""+placeID+"\"\n" +
                "}";


    }
}
