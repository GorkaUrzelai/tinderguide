package com.bergara.tinder.app;

public class GFX {


    // if you want to fetch data Online (from server) set : true.
    // if you want fatch data Offline (from asset) set    : false.
    public static boolean ONLINE_OFFLINE = true ;

    //Attention : after apply mode offline please active this choise to crypt your data
    // why Im using this : this methode it best choise to prtotect your code from reverse engine.
    public static boolean useCRYPTDATA = false ;

    // if you want to use ADS online set true.
    // if you want to use ADS offline set false and complet your ids
    public static boolean useONLINE_ADNETWORK = true ;

    // Your link server json her after choose online mode.
    //https://ia601403.us.archive.org/32/items/dataON/dataON.json
    public static String JSONLINK_on = "https://raw.githubusercontent.com/gurzelai/gurzelai.github.io/main/dataONTinder.json";

    // your name json file in assets :
    public static String JSONDATA_off = "dataOFF.json";

    // after apply mode ADS offline ! choose your ntewrok ads :
    // if you want to use AdMob enter : admob
    // if you want to use Facebook enter : facebook
    // if you want to use Mix Ads (Random ads between Facebook & admob) enter : mix
    public static String NetworkDefault = "admob";

    // AdMob Network Ads :
    public static String AdMob_Id = "CF0785CF051ADBA64C59CBAB2A35F7A9";  //AdMob Id For GDPR.
    public static String Banner_AdMob = "4E67E112CBFEEA0BA7668C724BD89E489C68BD29C16045CF36163B57EBD2D20C2F166DC4948F2EFC840A19650A1293F0";  //AdMob Banner Id
    public static String Interstitial_AdMob = "4E67E112CBFEEA0BA7668C724BD89E4895C4EFF64A09BC66DCE734063EB01BAE2220B56B425CD55A9F2BD3BA305A81EC"; //AdMob Interstitial Id
    public static String NativeAds_AdMob = "4E67E112CBFEEA0BA7668C724BD89E483BB67BF0BC6669D8174ACF947EA865B3E2E19525FF363C91CE44A297506C99D2"; //AdMob Native Id

    // Ids For Facebook Networks Ad :
    public static String Banner_AdUnit_Facebook = "D66430DD32AD74E3B36556288EE18B1F79385D3CD3A2002A48B997DA7B0AFA19F067A6C3097563375CECC1E17663BCC5549E5461C2B16199530E76308BBA7F99" ;
    public static String Interstitial_AdUnit_Facebook = "D66430DD32AD74E3B36556288EE18B1F79385D3CD3A2002A48B997DA7B0AFA1973FF8BC8EDA3FDE36B1F6BC0C7B65ED600FC17F6B018FB878FFDDAFCA5BB5B7B" ;
    public static String NativeAds_Facebook = "816DD29ED32999359D37B9BD87890F6FFAE8F3CA02CB56E2FB3D9255AA856619";


    // Applovin Network ADS :

    public static String Banner_Applovin ="gfhjkhgjkjhgjk";
    public static String interstitial_Applovin ="hjkhgjkhghjhgfhj";


    // true to show native ad in list iems :
    public static boolean showNativeAdInList = true ;
    // true to show native ad in Content (Tips detail) :
    public static boolean showNativeAdInContent = true ;

    // force loading list to load native same time:
    public static boolean forceNative = true ;


    // your name folder in assets to load image preview on List.
    public static String FOLDER_PREVIEW  = "preview";
    // your name font in assets :
    public static String ApplicationFont = "quest.ttf";

    // your link privacy policy her :
    public static String PrivacyPolicy = "";
    // your develper google play store name :
    public static String DeveloperName = "VOODOO";

    // If you want to use Music inside App set : true.
    public static boolean useMusic = true ;
    // If you want to use Particles stars effect set : true.
    public static boolean useParticles = true ;

    // if you want to show button next and preview in content set true.
    public static boolean ShowNEXT_PREVIEW = true ;
    //When show Next Button If you want to show Interstitial enter true :
    public static boolean ShowAdsWhenClickNext = true ;
    // next & preview counter number of ads and show it :
    public static int NumberofAdsCounter = 2 ;

    //Toast Text :
    public static String open_link_failed = "Failed to open the link";
    public static String open_package_failed = "The Game not installed";
   // Content Title
    public static String tips = "Tips";


    // This is Values From Json File : Don't change This :
    public static String JsObject = "GuideData" ;
    public static String JsArrayList = "items" ;
    public static String JsArray = "content" ;
    //For Guide Text data :
    public static String JsTitle = "title";
    public static String JsPreview = "image";
    public static String Jscontent = "text";
    public static String Jsimage = "image_link";
    public static String Jsorder = "ordered";
    public static String JStext_size ="text_size";
    public static String Jscolor = "color";
    public static String Jsstyle = "style";
    public static String Jsgravity = "gravity";
    public static String Jsleft = "left";
    public static String JsisLink = "isLink";
    public static String JssetLinks = "setLink";
    public static String JslinkTitle = "link_title";
    public static String JssetNativeAds = "isNative";
    public static String assetsPath = "file:///android_asset/";

    //For Ads data :
    public static String JsArrayAds = "AdsController" ;
    public static String JsObjectNetworkAds = "NetworkAds" ;
    public static String JsObjectAdID = "AdMobId" ;
    public static String JsObjectAdBanner = "BannerAdmob" ;
    public static String JsObjectAdInterstitial = "InterstitialAdmob" ;
    public static String JsObjectAdNative = "NativeAdmob" ;
    public static String JsObjectApplovinInterstitial = "ApplovinInterstitial" ;

    // Don't change This :
    public static class Tags{

        public static String MARKET ="market://details?id=";
        public static String GGAPPS ="http://play.google.com/store/apps/details?id=";
        public static String GGDEV ="https://play.google.com/store/apps/developer?id=";
        public static String HTTP ="http";
        public static String WWW ="www";
        public static String True ="true";
        public static String DONE ="done";
        public static String FAILED ="failed";
        public static String NotAvailableMessage ="There is no app available for this task";
        public static String AdMob = "admob";
        public static String Facebook = "facebook";
        public static String Applovin = "Applovin";
        public static String Mix = "mix";
    }


}
