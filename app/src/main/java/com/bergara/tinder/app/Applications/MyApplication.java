package com.bergara.tinder.app.Applications;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.RawRes;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.bergara.tinder.app.Async.SynAd;
import com.bergara.tinder.app.BuildConfig;
import com.bergara.tinder.app.GFX;
import com.bergara.tinder.app.Helper.Hexing;
import com.bergara.tinder.app.R;
import com.bergara.tinder.app.Helper.SettingsPreferences;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * This project create by SAID MOTYA on 06/17/2020.
 * contact on Facebook : https://web.facebook.com/motya.said
 * contact on Email : zonek.app@hotmail.com or zonek.app@gmail.com
 * it a free code source for member secret gfx
 */
public class MyApplication extends Application implements MaxAdListener {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    public static MediaPlayer player;
    public static MediaPlayer mMediaPlayer;

    private InterstitialAd mInterstitialAdMob;
    private AdView mAdViewAdMob;
    private RelativeLayout relativeLayout;

    private MaxInterstitialAd interstitialAd;
    private int retryAttempt;


    public String defualtAd = "";
    public String AdMobID = "";
    public String natAdMob = "";
    public String natfacebook = "";
    private String tag = "secret_gfx";
    private Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());
        buildRequestAd();
        player = new MediaPlayer();
        mediaPlayerInitializer();
        if (GFX.useMusic) {
            CheckMusic();
        }
        if (BuildConfig.DEBUG) {
            if (GFX.ONLINE_OFFLINE) {
                onlineData();
            } else {
                onfflineData();
            }
        }
    }

    private void onfflineData() {
        if (GFX.useCRYPTDATA) {
            if (!GFX.useONLINE_ADNETWORK) {
                cryptData();
            }
            return;
        }
        warningLog();

    }

    private void onlineData() {
        if (GFX.useCRYPTDATA) {
            getLinkData();
            return;
        }
        warningLog();


    }

    private void cryptData() {

        if (BuildConfig.DEBUG) {
            try {
                String admobId = Hexing.setStrings(GFX.AdMob_Id);
                String banner_admob = Hexing.setStrings(GFX.Banner_AdMob);
                String interstitial_admob = Hexing.setStrings(GFX.Interstitial_AdMob);
                String native_admob = Hexing.setStrings(GFX.NativeAds_AdMob);
                String banner_facebook = Hexing.setStrings(GFX.Banner_AdUnit_Facebook);
                String interstitial_facebook = Hexing.setStrings(GFX.Interstitial_AdUnit_Facebook);
                String native_facebook = Hexing.setStrings(GFX.NativeAds_Facebook);
                String Banner_applovin = Hexing.setStrings(GFX.Banner_Applovin);
                String interstitial_applovin = Hexing.setStrings(GFX.interstitial_Applovin);

                if (BuildConfig.DEBUG) {
                    Log.d(tag, "*******************AdMob ID crypt***********************");
                    Log.d(tag, "AdMob id is : " + admobId);
                    Log.d(tag, "Banner AdMob crypt is : " + banner_admob);
                    Log.d(tag, "Interstitial AdMob crypt is : " + interstitial_admob);
                    Log.d(tag, "Native AdMob crypt is : " + native_admob);
                    Log.d(tag, "*******************Facebook ID crypt***********************");
                    Log.d(tag, "Banner Facebook crypt is : " + banner_facebook);
                    Log.d(tag, "Interstitial Facebook crypt is : " + interstitial_facebook);
                    Log.d(tag, "Native Facebook crypt is : " + native_facebook);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getLinkData() {
        try {
            String link = Hexing.setStrings(GFX.JSONLINK_on);
            Log.d(tag, "new Link crypt is : " + link);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void warningLog() {
        if (BuildConfig.DEBUG) {
            Log.d(tag, "**********************************************************");
            Log.d(tag, "****** Attention crypt data not active @@@@@        ******");
            Log.d(tag, "****** Your App it be compiled by reverse engine !! ******");
            Log.d(tag, "****** I recommended to use crypt data              ******");
            Log.d(tag, "****** Contact me On Facebook:                      ******");
            Log.d(tag, "****** https://web.facebook.com/motya.said          ******");
            Log.d(tag, "**********************************************************");

        }
    }

    private void buildRequestAd() {

        if (GFX.useONLINE_ADNETWORK) {
            try {
                if (GFX.useCRYPTDATA) {
                    onAdOnline(Hexing.getStrings(GFX.JSONLINK_on));
                } else {
                    onAdOnline(GFX.JSONLINK_on);
                }
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "use ads online");
                }

            } catch (Exception e) {
            }
        } else {
            onAdOffline();
            if (BuildConfig.DEBUG) {
                Log.d("motya", "use ads offline");
            }
        }

    }

    private void onAdOffline() {

        if (GFX.NetworkDefault.equalsIgnoreCase(GFX.Tags.AdMob)) {
            try {

                if (GFX.useCRYPTDATA) {
                    if (BuildConfig.DEBUG) {
                        Log.d("motya", "Call RequestAdMob Crypt");
                    }
                    RequestAdMobAd(Hexing.getStrings(GFX.Banner_AdMob), Hexing.getStrings(GFX.Interstitial_AdMob));

                } else {
                    if (BuildConfig.DEBUG) {
                        Log.d("motya", "Call RequestAdMob Not Crypt");
                    }
                    RequestAdMobAd(GFX.Banner_AdMob, GFX.Interstitial_AdMob);
                }

            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "onAdOnline AdMob Build Failed causes :" + e);
                }
            }
        } else if (GFX.NetworkDefault.equalsIgnoreCase(GFX.Tags.Facebook)) {
            try {

                if (GFX.useCRYPTDATA) {
                    if (BuildConfig.DEBUG) {
                        Log.d("motya", "Call RequestFacebook Crypt");
                    }
                     } else {
                    if (BuildConfig.DEBUG) {
                        Log.d("motya", "Call RequestFacebook Not Crypt");
                    }
                }

            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "onAdOnline Facebook Build Failed causes :" + e);
                }
            }
        }
        else if (GFX.NetworkDefault.equalsIgnoreCase(GFX.Tags.Applovin)) {
            try {

                if (GFX.useCRYPTDATA) {
                    if (BuildConfig.DEBUG) {
                        Log.d("motya", "Call RequestFacebook Crypt");
                    }
                    RequestApplovinAd(Hexing.getStrings(GFX.interstitial_Applovin));
                } else {
                    if (BuildConfig.DEBUG) {
                        Log.d("motya", "Call RequestFacebook Not Crypt");
                    }
                    RequestApplovinAd(GFX.interstitial_Applovin);
                }

            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "onAdOnline Facebook Build Failed causes :" + e);
                }
            }
        }
        else if (GFX.NetworkDefault.equalsIgnoreCase(GFX.Tags.Mix)) {
            try {

                if (GFX.useCRYPTDATA) {
                    RequestAdMobAd(Hexing.getStrings(GFX.Banner_AdMob), Hexing.getStrings(GFX.Interstitial_AdMob));
                   RequestApplovinAd(Hexing.getStrings(GFX.interstitial_Applovin));
                } else {

                    RequestAdMobAd(GFX.Banner_AdMob, GFX.Interstitial_AdMob);
                    RequestApplovinAd(GFX.interstitial_Applovin);
                }

            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "onAdOnline Facebook Build Failed causes :" + e);
                }
            }
        }

    }

    @SuppressLint("StaticFieldLeak")
    private void onAdOnline(String link) {
        new SynAd(getApplicationContext(), link) {
            @Override
            protected void onDataPreExecute() {
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "onAdOnline loading...");
                }
            }

            @Override
            protected void onDataExecute(String result, String status) {
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "onAdOnline status is " + status);
                }
            }


            @Override
            protected void onAdResult(String networkDefault, String adMobID, String bannerAdMob, String interstitialAdMob, String nativeAdMo,String interstitialApplovin) {
                try {
                    if (networkDefault != null) {
                        defualtAd = networkDefault;
                    }
                    if (adMobID != null) {
                        AdMobID = adMobID;
                    }
                    if (nativeAdMob != null) {
                        natAdMob = nativeAdMob;
                    }


                    if (networkDefault.equalsIgnoreCase(GFX.Tags.AdMob)) {
                        try {
                            RequestAdMobAd(bannerAdMob, interstitialAdMob);
                        } catch (Exception e) {
                            if (BuildConfig.DEBUG) {
                                Log.d("motya", "onAdOnline AdMob Build Failed causes :" + e);
                            }
                        }
                    }
                    else if (networkDefault.equalsIgnoreCase(GFX.Tags.Applovin)) {
                        try {
                            RequestApplovinAd(interstitialApplovin);
                        } catch (Exception e) {
                            if (BuildConfig.DEBUG) {
                                Log.d("motya", "onAdOnline Ad Mix Build Failed causes :" + e);
                            }
                        }
                    }


                    else if (networkDefault.equalsIgnoreCase(GFX.Tags.Mix)) {
                        try {
                            RequestAdMobAd(bannerAdMob, interstitialAdMob);
                            RequestApplovinAd(interstitialApplovin);
                        } catch (Exception e) {
                            if (BuildConfig.DEBUG) {
                                Log.d("motya", "onAdOnline Ad Mix Build Failed causes :" + e);
                            }
                        }
                    }

                } catch (Exception e) {
                    if (BuildConfig.DEBUG) {
                        Log.d("motya", "Opps somthing happer in your server :" + e);
                        Log.d("motya", "send your error code to the developper" );
                    }
                }

            }
        }.execute();

    }

    public void RequestAdMobAd(String banner, String Interstitial) {
        // Initialize the AdMob Network SDK :
        MobileAds.initialize(this);

        //Load AdMob Interstitial :
        mInterstitialAdMob = new InterstitialAd(this);
        mInterstitialAdMob.setAdUnitId(Interstitial);
        final AdRequest adRequestInterstitial = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAdMob.loadAd(adRequestInterstitial);
        mInterstitialAdMob.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "Interstitial Loaded");
                }

            }


            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "Interstitial onAdFailedToLoad");
                }


            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAdMob.loadAd(adRequestInterstitial);
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "Interstitial onAdClosed");
                }

            }
        });


        mAdViewAdMob = new AdView(this);
        mAdViewAdMob.setAdSize(AdSize.SMART_BANNER);
        mAdViewAdMob.setAdUnitId(banner);
        AdRequest adRequestBanner = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdViewAdMob.loadAd(adRequestBanner);
        mAdViewAdMob.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "Banner on Loaded");
                }

                if (relativeLayout != null) {
                    setBannerAd(relativeLayout);
                }

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "Failed to load Banner");
                }


            }
        });

    }


    ///////////////////////////////Applovin////////////////////////////////////
    public void RequestApplovinAd(String Interstitial){

        interstitialAd = new MaxInterstitialAd( Interstitial, (Activity) context);
        interstitialAd.setListener( this );

        // Load the first ad
        interstitialAd.loadAd();
    }




    // Show Interstitial After Loading :
    public void showInterstitial() {


        try {

            if (GFX.useONLINE_ADNETWORK) {
                buildInterstitial(defualtAd);
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "Show Interstitial Online network using is : " + defualtAd);
                }

            } else {
                buildInterstitial(GFX.NetworkDefault);
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "Show Interstitial Offline network using is : " + GFX.NetworkDefault);
                }
            }
        }catch (Exception e){
            if (BuildConfig.DEBUG) {

                Log.d("motya", "Somthing Wrong About Interstitial recheck your code : " + e);
                Log.d("motya", "send your error code to the developper" );
            }
        }



    }

    // Show Banner in RelativeLayout :
    public void setBannerAd(RelativeLayout bannerView) {

        try {

            if (GFX.useONLINE_ADNETWORK) {
                buildBanner(defualtAd, bannerView);
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "Show Banner Online network using is : " + defualtAd);
                }
            } else {
                buildBanner(GFX.NetworkDefault, bannerView);
                if (BuildConfig.DEBUG) {
                    Log.d("motya", "Show Banner Offline network using is : " + GFX.NetworkDefault);
                }
            }

        }catch (Exception e){
            if (BuildConfig.DEBUG) {
                Log.d("motya", "Somthing Wrong About Banner recheck your code : " + e);
                Log.d("motya", "send your error code to the developper" );
            }
        }



    }




    private void buildInterstitial(String checking) {

        if (checking.equals(GFX.Tags.AdMob)) {
            setInterstitialAdMob();
        }   else if (checking.equals(GFX.Tags.Applovin)) {
            setInterstitialApplovin();
        }
        else if (checking.equals(GFX.Tags.Mix)) {
            int[] list1 = new int[]{0, 1};
            int random1 = new Random().nextInt(list1.length);
            setInterstitialSwitcher(random1);
        }

    }

    public void onDestroyBanner() {}

    private void setInterstitialSwitcher(int number) {
        switch (number) {
            case 0:
                setInterstitialAdMob();
                break;
            case 1:
                setInterstitialApplovin();
                break;

        }

    }

    private void setInterstitialAdMob() {
        // Show Interstitial AdMob After Loading
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
            mInterstitialAdMob.show();
        }
    }



    private void setInterstitialApplovin() {

        if ( interstitialAd.isReady() )
        {
            interstitialAd.showAd();
        }
    }


    private void buildBanner(String check, RelativeLayout relativeLayout) {

        if (check.equals(GFX.Tags.AdMob)) {
            setBannerAdMob(relativeLayout);
        } else if (check.equals(GFX.Tags.Facebook)) {
            //setBannerFacebook(relativeLayout);
        } else if (check.equals(GFX.Tags.Mix)) {

            int[] list2 = new int[]{0, 1};
            int random2 = new Random().nextInt(list2.length);
            setBannerSwitcher(random2, relativeLayout);

        }

    }

    private void setBannerSwitcher(int number2, RelativeLayout relativeLayout) {
        switch (number2) {
            case 0:
                setBannerAdMob(relativeLayout);
                break;
            case 1:
              //  setBannerFacebook(relativeLayout);
                break;
        }

    }

    private void setBannerAdMob(RelativeLayout relativeLayout) {
        if (mAdViewAdMob == null) {
            return;
        }
        if (mAdViewAdMob.getParent() != null) {
            ((ViewGroup) mAdViewAdMob.getParent()).removeView(mAdViewAdMob);
        }
        relativeLayout.removeAllViews();
        relativeLayout.addView(mAdViewAdMob);
        relativeLayout.invalidate();
    }



    public static void mediaPlayerInitializer() {
        try {
            player = MediaPlayer.create(getAppContext(), R.raw.music_bg);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setLooping(true);
            player.setVolume(1f, 1f);
        } catch (IllegalStateException e) {
            if (BuildConfig.DEBUG) {
                Log.d("music", "somthing wrong about music " + e);
            }
            e.printStackTrace();
        }
    }

    public static void PlayMusic() {
        try {
            if (SettingsPreferences.checkMusic(mContext) && !player.isPlaying()) {
                player.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            if (BuildConfig.DEBUG) {
                Log.d("music", "somthing wrong about music " + e);
            }
            mediaPlayerInitializer();
            player.start();
        }
    }

    public static void ClickSounds(Context context, @RawRes final int mRaw) {
        mMediaPlayer = MediaPlayer.create(getAppContext(), mRaw);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setVolume(0.2f, 0.2f);
        try {
            mMediaPlayer.prepare();

        } catch (Exception e) {
            e.printStackTrace();
            if (BuildConfig.DEBUG) {
                Log.d("motya", "somthing wrong about music " + e);
            }
        }

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mMediaPlayer = null;
            }
        });
        mMediaPlayer.start();
    }

    public static void StopSound() {
        try {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer.reset();
                mMediaPlayer.release();
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.d("music", "somthing wrong about music " + e);
            }
        }

    }

    public static void StopMuisc() {
        try {
            if (player.isPlaying()) {
                player.pause();
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.d("music", "somthing wrong about music " + e);
            }
        }


    }

    public void CheckMusic() {
        if (SettingsPreferences.checkMusic(getApplicationContext())) {
            try {
                PlayMusic();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        } else {
            try {
                StopMuisc();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setContext(Context context) {
        mContext = context;
    }

    public static Context getAppContext() {
        return mContext;
    }



    // MAX Ad Listener
    @Override
    public void onAdLoaded(final MaxAd maxAd)
    {
        // Interstitial ad is ready to be shown. interstitialAd.isReady() will now return 'true'

        // Reset retry attempt
        retryAttempt = 0;
    }

    @Override
    public void onAdLoadFailed(final String adUnitId, final MaxError error)
    {
        // Interstitial ad failed to load
        // We recommend retrying with exponentially higher delays up to a maximum delay (in this case 64 seconds)

        retryAttempt++;
        long delayMillis = TimeUnit.SECONDS.toMillis( (long) Math.pow( 2, Math.min( 6, retryAttempt ) ) );

        new Handler() {
            public void postDelayed(Runnable runnable, long delayMillis) {
            }

            @Override
            public void publish(LogRecord logRecord) {

            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        }.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                interstitialAd.loadAd();
            }
        }, delayMillis );
    }

    @Override
    public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error)
    {
        // Interstitial ad failed to display. We recommend loading the next ad
        interstitialAd.loadAd();
    }


    @Override
    public void onAdDisplayed(MaxAd ad) {

    }

    @Override
    public void onAdHidden(MaxAd ad) {

    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

}

