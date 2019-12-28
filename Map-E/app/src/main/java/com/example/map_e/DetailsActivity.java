package com.example.map_e;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.res.Resources;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.map_e.ui.main.SectionsPagerAdapter;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static java.security.AccessController.getContext;

public class DetailsActivity extends AppCompatActivity {

    private String id;
    TextView title;
    LatLng loc;
    private StreetViewPanorama panaroma;

    public class GetPlaceDetails extends AsyncTask<Object,String,String> {
        String googlePlacesData;
        String url;
        HashMap<String, Object> place;
        JSONObject placejson;

        @Override
        protected String doInBackground(Object... objects) {
            url = (String) objects[0];
            getURL downloadURL = new getURL();
            try {
                googlePlacesData = downloadURL.readUrl(url);
                //Log.d("doInBackground:", "url=" + googlePlacesData.toString() );
            } catch (IOException e) {
                e.printStackTrace();
            }
            return googlePlacesData;
        }

        @Override
        protected void onPostExecute(String s) {
            JSONObject jsonObject = null;
            try {
                jsonObject = (JSONObject) new JSONObject(googlePlacesData).get("result");
                JSONObject save = ((JSONObject) ((JSONObject) jsonObject.get("geometry")).get("location"));
                loc = new LatLng((double) (save.get("lat")), (double) (save.get("lng")));
                //Log.d("postExecute:", "jsonObject=" + jsonObject.toString());
                //create a hashmap and store all params
                Log.d("postExecute:", "loc=" + loc.toString());
                placejson = jsonObject;
                place = new Gson().fromJson(jsonObject.toString(), HashMap.class);
                Log.d("HashMap", place.toString());
                init();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private void init() {
            title.setText((String) place.get("name"));
            setPictures();
            setOpenHours();
            setStreetView();

            //setReviews();

            TextView address = findViewById(R.id.address);
            address.setText(place.get("formatted_address").toString());

            TextView address2 = findViewById(R.id.address2);
            address2.setText(place.get("formatted_address").toString());

            WebView website = findViewById(R.id.website);
            TextView websiteurl = findViewById(R.id.website_url);
            if (place.containsKey("website")) {
                website.loadUrl(place.get("website").toString());
                websiteurl.setText(place.get("website").toString());
            } else {
                website.loadUrl("#");
                websiteurl.setText("No website information is given.");
            }

            TextView phone = findViewById(R.id.phone);
            if (place.containsKey("formatted_phone_number")) {
                phone.setText(place.get("formatted_phone_number").toString());
            } else {
                phone.setText("No phone information is given.");
            }

            TextView phone2 = findViewById(R.id.phone2);
            if (place.containsKey("international_phone_number")) {
                phone2.setText(place.get("international_phone_number").toString());
            } else {
                phone2.setText("No international phone information is given.");
            }


            TextView price = findViewById(R.id.price_level);
            if (place.containsKey("price_level")) {
                price.setText("Price Level: " + place.get("price_level").toString() + "/4.0");
            } else {
                price.setText("No price level information given.");
            }

        }

        public void setReviews() {
            //ArrayList<String> names = new ArrayList<>();
            //ArrayList<String> times = new ArrayList<>();
            //ArrayList<String> ratings = new ArrayList<>();
            //ArrayList<String> texts = new ArrayList<>();

            TextView users = findViewById(R.id.numreviews);
            if (place.containsKey("user_ratings_total")) {
                users.setText(place.get("user_ratings_total").toString());
            } else {
                users.setText("No user has submitted a rating yet.");
            }

            TextView rating = findViewById(R.id.overallrating);
            if (place.containsKey("rating")) {
                rating.setText(place.get("rating").toString());
            } else {
                rating.setText("No overall rating information for this location.");
            }

            JSONArray reviews = null;
            try {
                reviews = placejson.getJSONArray("reviews");
            //    if (place.containsKey("reviews")) {
            //        for (int i = 0; i < reviews.length(); i++) {
            //            JSONObject cur = reviews.getJSONObject(i);
            //            names.add(cur.getString("author_name"));
            //            times.add(cur.getString("relative_time_description"));
            //            ratings.add(cur.getString("rating"));
            //            texts.add(cur.getString("text"));
            //       }
            //        Log.d("reviews", texts.toString());

                    TextView revs = findViewById(R.id.review);
                    revs.setText(reviews.getJSONObject(0).getString("text"));
            //    }

                /*LinearLayoutManager layoutManager = new LinearLayoutManager(DetailsActivity.this, LinearLayoutManager.VERTICAL, false);
                //View view = (View) findViewById(R.id.innerlayout);
                RecyclerView recyclerView = findViewById(R.id.reviewss);
                //TextView users = findViewById(R.id.users);
                if (place.containsKey("user_ratings_total")) {
                //    users.setText(place.get("user_ratings_total").toString());
                } else {
                //    users.setText("No user has submitted a rating yet.");
                }

                //TextView rating = view.findViewById(R.id.overall_rating);
                if (place.containsKey("rating")) {
                //    rating.setText(place.get("rating").toString());
                } else {
                //    rating.setText("No overall rating information for this location.");
                }

                recyclerView.setLayoutManager(layoutManager);
                ReviewsAdapter adapter = new ReviewsAdapter(DetailsActivity.this, names,ratings,times,texts);
                recyclerView.setAdapter(adapter);*/

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        public void setStreetView() {
            StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/streetview?size=600x300&&key=AIzaSyDof3B7wdhVWKZw0XFAM-uSMb7qNd4-Pv8&location=");
            googlePlaceUrl.append(loc.latitude + "," + loc.longitude);
            Glide.with(DetailsActivity.this).load(googlePlaceUrl.toString()).into((ImageView) findViewById(R.id.streetviewpanorama));
        }

        public void setPictures() {
            ArrayList<String> images = new ArrayList<>();
            JSONArray photos = null;
            try {
                photos = placejson.getJSONArray("photos");
                if (place.containsKey("photos")) {
                    for (int i = 0; i < photos.length(); i++) {
                        StringBuilder string =  new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?maxwidth=120&key=AIzaSyDof3B7wdhVWKZw0XFAM-uSMb7qNd4-Pv8&photoreference=");
                        String ref = photos.getJSONObject(i).getString("photo_reference");
                        string.append(ref);
                        Log.d("Photos", string.toString());

                        images.add(string.toString());
                    }
                    LinearLayoutManager layoutManager = new LinearLayoutManager(DetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    RecyclerView recyclerView = findViewById(R.id.placeimgs);
                    recyclerView.setLayoutManager(layoutManager);
                    PhotosAdapter adapter = new PhotosAdapter(DetailsActivity.this,images);
                    recyclerView.setAdapter(adapter);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void setOpenHours() {
            TextView hours = findViewById(R.id.hours);
            TextView details = findViewById(R.id.hours_detail);
            Log.d("Check", "this is:" + place.containsKey("opening_hours"));
            if (place.containsKey("opening_hours")) {
                try {
                    Log.d("opening hours", placejson.getJSONObject("opening_hours").toString());
                    JSONObject open = placejson.getJSONObject("opening_hours");
                    if (!open.isNull("open_now")) {
                        Boolean isit = (boolean) open.get("open_now");
                        if (isit) {
                            hours.setText("Currently Open.");
                        } else {
                            hours.setText("Currently Closed.");
                        }
                    }
                    if (!open.isNull("weekday_text")) {
                        JSONArray save = open.getJSONArray("weekday_text");
                        StringBuilder record = new StringBuilder("");
                        for (int i = 0; i < save.length(); i++) {
                            Log.d("items", save.getString(i));
                            record.append(save.getString(i) + '\n');
                        }
                        Log.d("overall", record.toString());
                        details.setText(record.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        title = findViewById(R.id.title);
        ImageButton backB = (ImageButton) findViewById(R.id.returnButton);
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });


        id = getIntent().getStringExtra("marker");
        getUrlForHere(id);

    }
    public void getUrlForHere(String id) {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json?");
        googlePlaceUrl.append("place_id="+id);
        googlePlaceUrl.append("&key="+"AIzaSyDof3B7wdhVWKZw0XFAM-uSMb7qNd4-Pv8");
        GetPlaceDetails placeDetails = new GetPlaceDetails();
        Object dataTransfer[] = new Object[1];
        dataTransfer[0] = googlePlaceUrl.toString();
        Log.d("DetailsActivity", googlePlaceUrl.toString());
        placeDetails.execute(dataTransfer);
    }
    public void clicked(View view) {
        Intent intent = new Intent(DetailsActivity.this, AddReviewActivity.class);
        startActivity(intent);
    }
}
