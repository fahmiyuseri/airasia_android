package com.ipay88.airasia;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CountryJSON  {

   public static List<Country> CountryList(){
       String json = "\n" +
               "{\"items\" : [\n" +
               "{ \"Country\": \"Malaysia\",\n" +
               "  \"CountryCode\": \"MY\",\n" +
               "  \"Street\": \"Kuala Lumpur\",\n" +
               "  \"StreetCode\": \"KUL\",\n" +
               "\"City\": \"Kuala Lumpur\",\n" +
               "  \"CityCode\": \"KUL\",\n" +
               "  \"Lat\": \"3.1375625\",\n" +
               "  \"lgt\": \"101.4070253\"},\n" +
               "{ \"Country\": \"Malaysia\",\n" +
               "  \"CountryCode\": \"MY\",\n" +
               "  \"Street\": \"Kelantan\",\n" +
               "  \"StreetCode\": \"KEL\",\n" +
               "\"City\": \"Kota Bharu\",\n" +
               "  \"CityCode\": \"KBR\",\n" +
               "  \"Lat\": \"6.1697775\",\n" +
               "  \"lgt\": \"102.2909929\"},\n" +
               "{ \"Country\": \"Malaysia\",\n" +
               "  \"CountryCode\": \"MY\",\n" +
               "  \"Street\": \"Pulau Penang\",\n" +
               "  \"StreetCode\": \"PNG\",\n" +
               "\"City\": \"Penang\",\n" +
               "  \"CityCode\": \"PNG\",\n" +
               "  \"Lat\": \"5.2934345\",\n" +
               "  \"lgt\": \"100.2693267\"}\n" +
               "\n" +
               "]\n" +
               "}";
        List<Country> countries = new ArrayList<>();
       try {
           JSONArray items = new JSONObject(json).getJSONArray("items");
           for( int i=0;i<items.length();i++ ){
               Country country = (Country) new Gson().fromJson(items.getJSONObject(i).toString(),Country.class);
               countries.add(country);
           }
       } catch (JSONException e) {
           e.printStackTrace();
       }

       return countries;
   }
}
