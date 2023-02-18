package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public TestDataBuild(){}

   public AddPlace addPlacePayload(String name,String language,String address){
       AddPlace addPlace = new AddPlace();
       addPlace.setAccuracy(50);
       addPlace.setName(name);
       addPlace.setPhone_number("(+90)323 343 23 21");
       addPlace.setAddress(address);
       addPlace.setWebsite("http://google.com");
       addPlace.setLanguage(language);

       List<String> myList = new ArrayList<String>();
       myList.add("shoe double park");
       myList.add("shop");
       addPlace.setTypes(myList);

       Location location = new Location();
       location.setLat( -38.383494);
       location.setLng(33.427362);
       addPlace.setLocation(location);
       return addPlace;
   }
   public String deletePlacePayload(String placeId){
        return "{\n" +
                "        \"place_id\": \"placeId\"\n" +
                "}";
   }

}
