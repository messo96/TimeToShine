package com.example.timetoshine;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bnvMain;
    private Fragment selectedFragment = null;
    private String tagFragment;
    private boolean flagBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_main_home);
        flagBack = getIntent().getBooleanExtra("flagBack", false);

        findViewById(R.id.logo_ippo_club).setOnClickListener(l->{
            Uri uri = Uri.parse("https://www.instagram.com/clubippodromo/");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/clubippodromo/")));
            }
        });


        findViewById(R.id.logo_comune_cesena).setOnClickListener(l->{
            Uri uri = Uri.parse("https://www.instagram.com/comune_di_cesena/");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/comune_di_cesena/")));
            }
        });



        findViewById(R.id.logo_la_forlivese).setOnClickListener(l ->{
            Uri uri = Uri.parse("https://www.instagram.com/torrefazione_la_forlivese/");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/torrefazione_la_forlivese/")));
            }
        });


        findViewById(R.id.logo_45_giri).setOnClickListener(l ->{
            Uri uri = Uri.parse("https://www.instagram.com/45girifrisbee/");
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/45girifrisbee/")));
            }
        });

        findViewById(R.id.directionButton).setOnClickListener(l ->{
            Uri gmmIntentUri = Uri.parse("google.navigation:q= 44.146038, 12.227971");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        findViewById(R.id.calendarButton).setOnClickListener(l ->{
            Utilities.insertFragment(this, new HomeFragment(), "Home", R.id.fragment_container_view);
            setContentView(R.layout.activity_main);

            bnvMain = findViewById(R.id.bottom_navigation_main);

            bnvMain.setOnItemSelectedListener(item ->{
                switch(item.getItemId()) {
                    case R.id.home_button_nav:
                        selectedFragment = new HomeFragment();
                        tagFragment = "Home";
                        break;

                    case R.id.tournament_button_nav:
                        selectedFragment = new TournamentFragment();
                        tagFragment = "Tournament";
                        break;

                    case R.id.sotg_button_nav:
                        selectedFragment= new SOTGFragment();
                        tagFragment= "SOTG";
                        break;
                }

                Utilities.insertFragment(this, selectedFragment, tagFragment, R.id.fragment_container_view);
                return true;
            });
        });


    }


    @Override
    public void onBackPressed() {
        Intent intent;
        if(!flagBack) {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("flagBack", true);
        }
        else{
            intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            flagBack = false;
        }
        startActivity(intent);
    }
}