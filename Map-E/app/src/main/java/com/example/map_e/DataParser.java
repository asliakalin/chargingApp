package com.example.map_e;

import java.util.HashMap;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DataParser {

    /**
     * We will call the parse method it will pass the json data with a lot of places
     * It will send this list of jsons to get places method
     * then in getplaces method it'll call get place method in a for loop and
     * it will fetch each element from the json and store it in placeslist
     * then return it
     */

    private HashMap<String, String> getPlace(JSONObject googlePlaceJson) {

        //create a hashmap and store all params
        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String placeName = "--NA--";
        String latitude= "";
        String longitude="";
        String address="";
        String place_id="";
        try {
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }
            // calculate stuff to put on the hashmap for the station
            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");
            address = googlePlaceJson.getString("formatted_address");
            place_id = googlePlaceJson.getString("place_id");
            googlePlaceMap.put("place_name", placeName);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("address", address);
            googlePlaceMap.put("place_id", place_id);
            googlePlaceMap.put("json", googlePlaceJson.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlaceMap;

    }

    private List<HashMap<String, String>>getPlaces(JSONArray jsonArray) {
        int count = jsonArray.length();
        // create a list of locations as JSONArray for nearby places
        List<HashMap<String, String>> placelist = new ArrayList<>();

        HashMap<String, String> placeMap = null;
        for(int i = 0; i<count;i++) {
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(i));
                placelist.add(placeMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placelist;
    }

    public List<HashMap<String, String>> parse(String jsonData) {
        //will return a list of hashmaps.
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        Log.d("json data", jsonData);

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }
}
