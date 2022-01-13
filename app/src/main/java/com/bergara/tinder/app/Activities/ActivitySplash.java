package com.bergara.tinder.app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.bergara.tinder.app.R;

/**
 * This project create by SAID MOTYA on 06/17/2020.
 * contact on Facebook : https://web.facebook.com/motya.said
 * contact on Email : zonek.app@hotmail.com or zonek.app@gmail.com
 * it a free code source for member secret gfx
*/

public class ActivitySplash extends AppCompatActivity {

    private int counter = 0 ;
    private ProgressBar progressBar;
    private TextView loading ;
    private Handler handler = new Handler();
    private int waited = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progressBar);
        loading = findViewById(R.id.loading);
        setProgress();


        // Please make sure to set the mediation provider value to "max" to ensure proper functionality
        AppLovinSdk.getInstance( this ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
            }
        } );
    }

    public void setProgress(){

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    while (counter < 100) {
                        counter += 1;
                        handler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(counter);
                                loading.setText(getString(R.string.splash_loading)+"  "+counter);
                            }
                        });
                        sleep(waited);
                    }
                } catch (InterruptedException e) {
                    e.toString();
                }

                finally {
                    Intent localIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(localIntent);
                    finish();
                }
            }

        };
        splashTread.start();
    }

}