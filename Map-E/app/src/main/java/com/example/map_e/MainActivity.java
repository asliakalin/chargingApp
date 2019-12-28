package com.example.map_e;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private static final String TAG = "MainActivity";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mTypes = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpCards();
    }

    private void setUpCards() {
        mImageUrls.add("https://d3h256n3bzippp.cloudfront.net/A3-White-background-2.jpg");
        mTypes.add("Plug-in Hybrid");
        mNames.add("Audi A3 2016");

        mImageUrls.add("https://d3h256n3bzippp.cloudfront.net/ds-3-crossback-white.jpg");
        mTypes.add("Battery Electric");
        mNames.add("DS 3 Crossback");

        mImageUrls.add("https://d3h256n3bzippp.cloudfront.net/mercedes-eqc-white-background.png");
        mTypes.add("Battery Electric");
        mNames.add("Mercedes EQC");

        mImageUrls.add("https://d3h256n3bzippp.cloudfront.net/e-tron-white-background-2.jpg");
        mTypes.add("Battery Electric");
        mNames.add("Audi e-Tron 2019");

        mImageUrls.add("https://d3h256n3bzippp.cloudfront.net/3303-2019-white-2.jpg");
        mTypes.add("Plug-in Hybrid");
        mNames.add("BMW 330e");

        mImageUrls.add("https://d3h256n3bzippp.cloudfront.net/MG_ZS_EV_white_background.png");
        mTypes.add("Battery Electric");
        mNames.add("MG ZS EV");

        mImageUrls.add("https://d3h256n3bzippp.cloudfront.net/q5-tfsie-white-background.png");
        mTypes.add("Plug-in Hybrid");
        mNames.add("Audi Q5 TFSI");

        mImageUrls.add("https://d3h256n3bzippp.cloudfront.net/Outlander-white-background-2.jpg");
        mTypes.add("Plug-in Hybrid");
        mNames.add("Mitsubishi Outlander");

        mImageUrls.add("https://d3h256n3bzippp.cloudfront.net/Ford-white-background-2.jpg");
        mTypes.add("Battery Electric");
        mNames.add("Ford Focus Electric");


        mImageUrls.add("https://d3h256n3bzippp.cloudfront.net/C-Zero-white-background-2.jpg");
        mTypes.add("Battery Electric");
        mNames.add("Citroen C-Zero");

        initRecycleView();
    }

    private void initRecycleView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mTypes, mImageUrls);
        recyclerView.setAdapter(adapter);
        if (isServicesOK()) {
            init();
        }
    }

    private void init(){
        ImageButton btn = (ImageButton) findViewById(R.id.nextButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public void tappedButtonFade(View view) {
        ImageButton btn =(ImageButton) findViewById(R.id.nextButton);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.pop);
        btn.startAnimation(animation);
    }

    public void tappedButtonPop(View view) {
        RecyclerView chunk = findViewById(R.id.recyclerView);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.pop);
        chunk.startAnimation(animation);
    }


    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occurred but we can resolve it
            Log.d(TAG, "isServicesOK: an error occurred but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


}
