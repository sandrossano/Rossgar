package com.example.sandro.irsina;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.sandro.irsina.Fuori.numero_random_fuori;
import static com.example.sandro.irsina.Lingua.deleteCache;
import static com.example.sandro.irsina.Lingua.logHeap;
import static com.example.sandro.irsina.Lingua.logValue;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView webView;
    static ViewPager viewPager;
    static int banner;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    int currentPage = 0;
    static int width_device=0;
    static Timer swipeTimer;
    static int height_device=0;
    static int numero_random=0;
    static Integer[] XMEN;
    int cacca_selezionata_iniziale=0;
    static int ordine=0;
    public boolean hasNavBar (Resources resources)
    {
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && resources.getBoolean(id);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        Log.d("lingua",Locale.getDefault().getLanguage());
        Log.d("linguaDisplay",Locale.getDefault().getDisplayLanguage());

        setContentView(R.layout.activity_main);

        findViewById(R.id.pager_banner).setVisibility(View.VISIBLE);
        findViewById(R.id.pager_banner_fuori).setVisibility(View.GONE);
        /*boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        boolean hasHomeKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_HOME);

        if (hasBackKey && hasHomeKey) {
            findViewById(R.id.include_main).setVisibility(View.GONE);
            findViewById(R.id.include_main).setEnabled(false);
            findViewById(R.id.include_main_2).setVisibility(View.VISIBLE);

            // no navigation bar, unless it is enabled in the settings
        } else {
            findViewById(R.id.include_main).setVisibility(View.VISIBLE);
            findViewById(R.id.include_main_2).setEnabled(false);
            findViewById(R.id.include_main_2).setVisibility(View.GONE);

            // 99% sure there's a navigation bar
        }*/

        if(hasNavBar(getResources())==true){
            findViewById(R.id.include_main).setVisibility(View.VISIBLE);
            findViewById(R.id.include_main_2).setEnabled(false);
            findViewById(R.id.include_main_2).setVisibility(View.GONE);
        }
        else{
            findViewById(R.id.include_main).setVisibility(View.GONE);
            findViewById(R.id.include_main).setEnabled(false);
            findViewById(R.id.include_main_2).setVisibility(View.VISIBLE);
        }

if (findViewById(R.id.include_main_2).getVisibility()==View.GONE){cacca_selezionata_iniziale=1;}
else{cacca_selezionata_iniziale=2;}

if (cacca_selezionata_iniziale==1){
        webView = (WebView)findViewById( R.id.webview_main );}
        else{
    webView = (WebView)findViewById( R.id.webview_main_2 );
}

        webView.setVisibility(View.INVISIBLE);
        Lingua.mContent=getApplicationContext();

        logHeap();
        logValue();

        deleteCache(getApplicationContext());
        clearApplicationData();
        ImageView ib;
        if (cacca_selezionata_iniziale==1){
       ib=(ImageView)findViewById(R.id.cambio);}
        else{ib=(ImageView)findViewById(R.id.cambio_2);}
        Locale current = getResources().getConfiguration().locale;
        if(current.getLanguage().equals("en")) {
            ib.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.flag_unionjack));
        }
        if(current.getLanguage().equals("it")) {
            ib.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.flag_italy));
        }
        if(current.getLanguage().equals("fr")) {
            ib.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.flag_france));
        }


        TextView scegli=(TextView) findViewById(R.id.textView14);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackground(getResources().getDrawable(R.drawable.color_back_2));
        setSupportActionBar(toolbar);
        setTitle("Comune di Irsina");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setUpAdapter();


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);




        //webView.setInitialScale( 1 );
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom( true );
        webView.getSettings().setLoadWithOverviewMode( true ); // Zoom farthest out

        webView.getSettings().setUseWideViewPort( true );
        webView.getSettings().setBuiltInZoomControls( true );
        webView.setInitialScale(1);

        webView.getSettings().setAllowFileAccess(true);

        webView.clearHistory();
        webView.clearFormData();
        webView.clearCache(true);

        WebSettings webSettings = webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        //webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        final int finalCacca_selezionata_iniziale = cacca_selezionata_iniziale;
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.scrollTo(0,(webView.getHeight()/2)-40);
                ImageView imm;
                if(finalCacca_selezionata_iniziale ==1) {
                     imm = (ImageView) findViewById(R.id.logo_webmain);
                }
                else {
                     imm = (ImageView) findViewById(R.id.logo_webmain_2);
                }
                imm.setVisibility(View.GONE);
                imm.destroyDrawingCache();
                webView.setVisibility(View.VISIBLE);
            }


            @SuppressLint("NewApi")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String[] a=url.split("attrazione=");
                if(a[1].equals("cattedrale")) {
                    Intent intent = new Intent(MainActivity.this, Cattedrale.class);
                    startActivity(intent);
                    swipeTimer.cancel();
                    swipeTimer.purge();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                }
                if(a[1].equals("sanfrancesco")) {
                    Intent intent = new Intent(MainActivity.this, SanFrancesco.class);
                    startActivity(intent);
                    swipeTimer.cancel();
                    swipeTimer.purge();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                }
                if(a[1].equals("museo")) {
                    Intent intent = new Intent(MainActivity.this, Museo.class);
                    startActivity(intent);
                    swipeTimer.cancel();
                    swipeTimer.purge();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                }
                if(a[1].equals("porticella")) {
                    Intent intent = new Intent(MainActivity.this, Porticella1.class);
                    startActivity(intent);
                    swipeTimer.cancel();
                    swipeTimer.purge();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                }
                if(a[1].equals("muretto")) {
                    Intent intent = new Intent(MainActivity.this, Muretto1.class);
                    startActivity(intent);
                    swipeTimer.cancel();
                    swipeTimer.purge();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                }
                if(a[1].equals("nuget")) {
                    Intent intent = new Intent(MainActivity.this, Nuget1.class);
                    startActivity(intent);
                    swipeTimer.cancel();
                    swipeTimer.purge();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                }
                return true;
            }
        });

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        width_device=width;
        height_device=height;


        String html="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title>Mappa</title>\n" +
                "\t<style>\n" +
                "\n" +
                ".card {\n" +
                "    box-shadow: 8px 8px 8px 8px rgba(0,0,0,0.2);\n" +
                "    transition: 0.3s;\n" +
                "    width: 700px;\n" +
                "    background-color:#FFF;\n" +
                "    float:left;\n" +
                "}\n" +
                ".card:hover {\n" +
                "    box-shadow: 0 8px 8px 0 rgba(0,0,0,0.2);\n" +
                "}\n" +
                "\n" +

                ".container {\n" +
                "    padding: 2px 15px;\n" +
                "\n" +
                "}\n" +
                "</style>\n" +
                "\t<script type=\"text/javascript\">\n" +
                "\t\tfunction showDiv() {\n" +
                "\t\t\tif(document.getElementById('card').style.display == \"block\")document.getElementById('card').style.display = \"none\";\n" +
                "   \t\t\telse {document.getElementById('card').style.display = \"block\";" +
                "               document.getElementById('card2').style.display = \"none\";" +
                "                      document.getElementById('card3').style.display = \"none\";" +
                "                      document.getElementById('card4').style.display = \"none\";" +
                "                       document.getElementById('card5').style.display = \"none\";" +
                "               document.getElementById('card1').style.display = \"none\";}\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tfunction showDiv1() {\n" +
                "\t\t\tif(document.getElementById('card1').style.display == \"block\")document.getElementById('card1').style.display = \"none\";\n" +
                "   \t\t\telse {document.getElementById('card1').style.display = \"block\";" +
                "               document.getElementById('card2').style.display = \"none\";" +
                "                      document.getElementById('card3').style.display = \"none\";" +
                "                      document.getElementById('card4').style.display = \"none\";" +
                "                       document.getElementById('card5').style.display = \"none\";" +
                "               document.getElementById('card').style.display = \"none\";}\n" +
                "\t\t}\n" +
                "\n" +

                "function showDiv2() {" +
                "               if(document.getElementById('card2').style.display == \"block\")document.getElementById('card2').style.display = \"none\";\n" +
                "                else {document.getElementById('card2').style.display = \"block\"; " +
                "                      document.getElementById('card1').style.display = \"none\";" +
                "                      document.getElementById('card3').style.display = \"none\";" +
                "                      document.getElementById('card4').style.display = \"none\";" +
                "                       document.getElementById('card5').style.display = \"none\";" +
                "                      document.getElementById('card').style.display = \"none\";}" +
                "}\n" +
                "function showDiv3() {" +
                "               if(document.getElementById('card3').style.display == \"block\")document.getElementById('card3').style.display = \"none\";\n" +
                "                else {document.getElementById('card3').style.display = \"block\"; " +
                "                      document.getElementById('card2').style.display = \"none\";" +
                "                      document.getElementById('card4').style.display = \"none\";" +
                "                       document.getElementById('card').style.display = \"none\";" +
                "                       document.getElementById('card5').style.display = \"none\";" +
                "                      document.getElementById('card1').style.display = \"none\";}" +
                "}\n" +
                "function showDiv5() {" +
                "               if(document.getElementById('card5').style.display == \"block\")document.getElementById('card5').style.display = \"none\";\n" +
                "                else {document.getElementById('card5').style.display = \"block\"; " +
                "                      document.getElementById('card2').style.display = \"none\";" +
                "                      document.getElementById('card4').style.display = \"none\";" +
                "                       document.getElementById('card1').style.display = \"none\";" +
                "                       document.getElementById('card').style.display = \"none\";" +
                "                      document.getElementById('card3').style.display = \"none\";}" +
                "}\n" +
                "function showDiv4() {" +
                "               if(document.getElementById('card4').style.display == \"block\")document.getElementById('card4').style.display = \"none\";\n" +
                "                else {document.getElementById('card4').style.display = \"block\"; " +
                "                      document.getElementById('card3').style.display = \"none\";" +
                "                      document.getElementById('card1').style.display = \"none\";" +
                "                       document.getElementById('card').style.display = \"none\";" +
                "                       document.getElementById('card5').style.display = \"none\";" +
                "                      document.getElementById('card2').style.display = \"none\";}" +
                "}" +
                "</script>\n" +
                "</head>\n" +
                "<body style=\"background-color:#dfd1b0;\">\n" +
                "\n" +
                "<img src=\"./mappa.png\" width=\"1800px\" height=\"3200px\"alt=\"mapppa\">\n" +

                "<div id=\"card\" style=\"position:absolute;top:1280px; left:220px;display:none;\">\n" +
                "<div class=\"card\">\n" +
                "\t<div class=\"container\">\n" +
                "\t\t<a style=\"text-decoration: none;\" href=\"https://www.google.it/attrazione=cattedrale\">"+
                "<img src=\"./visit.png\" alt=\"Avatar\" style=\"margin-top:35px; margin-right:5px; float:right;width:25%\"></a>\n" +
                "<div style=\"margin-left:10px;\">"+
                "\t\t<h1 style=\"font-size:260%;\"><b>Cattedrale di Santa Maria Assunta</b></h1>\n" +
                "\n" +
                "\t\t<p style=\"font-size:220%;\">Piazza XX Settembre, 9</p></div>\n" +
                "\n" +
                "\n" +
                "\t</div>\n" +
                "</div>\n" +
                "</div>\n" +

                "<div id=\"card1\" style=\"position:absolute;top:1555px; left:870px;display:none;\">\n" +
                "<div class=\"card\">\n" +
                "\t<div class=\"container\">\n" +
                "\t\t<a style=\"text-decoration: none;\" href=\"https://www.google.it/attrazione=sanfrancesco\">"+
                "<img src=\"./visit.png\" alt=\"Avatar\" style=\"margin-top:35px; margin-right:5px; float:right;width:25%\"></a>\n" +
                "<div style=\"margin-left:10px;\">"+
                "\t\t<h1 style=\"font-size:260%;\"><b>Chiesa San Francesco D'Assisi</b></h1>\n" +
                "\n" +
                "\t\t<p style=\"font-size:220%;\">Largo San Francesco, 10</p></div>\n" +
                "\n" +
                "\n" +
                "\t</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "" +
                "<div id=\"card2\" style=\"position:absolute;top:2000px; left:630px;display:none;\">" +
                "                <div class=\"card\">" +
                "                <div class=\"container\">\n" +
                "                <a style=\"text-decoration: none;\" href=\"https://www.google.it/attrazione=museo\">" +
                "                <img src=\"./visit.png\" alt=\"Avatar\" style=\"margin-top:15px; margin-right:5px; float:right;width:25%\"></a>" +
                "                <div style=\"margin-left:10px;\">" +
                "                <h1 style=\"font-size:260%;\"><b>Museo Janora</b></h1>" +
                "                " +
                "                <p style=\"font-size:220%;\">Largo San Francesco, 8</p></div>" +
                "                " +
                "                </div>" +
                "                </div>" +
                "                </div>" +
                ""+
                "<div id=\"card3\" style=\"position:absolute;top:2235px; left:380px;display:none;\">" +
                "                <div class=\"card\">" +
                "                <div class=\"container\">\n" +
                "                <a style=\"text-decoration: none;\" href=\"https://www.google.it/attrazione=porticella\">" +
                "                <img src=\"./visit.png\" alt=\"Avatar\" style=\"margin-top:15px; margin-right:5px; float:right;width:25%\"></a>" +
                "                <div style=\"margin-left:10px;\">" +
                "                <h1 style=\"font-size:260%;\"><b>Porticella</b></h1>" +
                "                " +
                "                <p style=\"font-size:220%;\">via Porticella</p></div>" +
                "                " +
                "                </div>" +
                "                </div>" +
                "                </div>" +
                "<div id=\"card4\" style=\"position:absolute;top:1500px; left:410px;display:none;\">" +
                "                <div class=\"card\">" +
                "                <div class=\"container\">\n" +
                "                <a style=\"text-decoration: none;\" href=\"https://www.google.it/attrazione=muretto\">" +
                "                <img src=\"./visit.png\" alt=\"Avatar\" style=\"margin-top:15px; margin-right:5px; float:right;width:25%\"></a>" +
                "                <div style=\"margin-left:10px;\">" +
                "                <h1 style=\"font-size:260%;\"><b>Antiche Mura</b></h1>" +
                "                " +
                "                <p style=\"font-size:220%;\">Via Ascensione</p></div>" +
                "                " +
                "                </div>" +
                "                </div>" +
                "                </div>" +
                "<div id=\"card5\" style=\"position:absolute;top:1200px; left:700px;display:none;\">" +
                "                <div class=\"card\">" +
                "                <div class=\"container\">\n" +
                "                <a style=\"text-decoration: none;\" href=\"https://www.google.it/attrazione=nuget\">" +
                "                <img src=\"./visit.png\" alt=\"Avatar\" style=\"margin-top:15px; margin-right:5px; float:right;width:25%\"></a>" +
                "                <div style=\"margin-left:10px;\">" +
                "                <h1 style=\"font-size:260%;\"><b>Palazzo Nugent</b></h1>" +
                "                " +
                "                <p style=\"font-size:220%;\">Piazza Garibaldi</p></div>" +
                "                " +
                "                </div>" +
                "                </div>" +
                "                </div>" +

                ""+
                "<div onclick=\"showDiv()\">\n" +
                "\t<img src=\"./cattedrale_irsina.png\" alt=\"ciao\" width=\"150px\" height=\"150px\" style=\"position: absolute;top: 1100px; left: 350px \" >\n" +
                "</div>\n" +
                "\n "+
                "<div onclick=\"showDiv1()\">\n" +
                "\t<img src=\"./sanfrancesco.png\" alt=\"ciao\" width=\"150px\" height=\"150px\" style=\"position: absolute;top: 1825px; left: 1350px \" >\n" +
                "</div>\n" +
                "" +
                "<div onclick=\"showDiv2()\">" +
                "<img src=\"./museo_janora.png\" alt=\"ciao\" width=\"150px\" height=\"150px\" style=\"position: absolute;top: 2000px; left: 1400px \" >\n" +
                "</div>\n" +
                "" +
                "<div onclick=\"showDiv3()\">" +
                "<img src=\"./porticella.png\" alt=\"ciao\" width=\"150px\" height=\"150px\" style=\"position: absolute;top: 2470px; left: 720px \" >\n" +
                "</div>\n" +
                "<div onclick=\"showDiv4()\">" +
                "<img src=\"./muretto.png\" alt=\"ciao\" width=\"150px\" height=\"150px\" style=\"position: absolute;top: 1650px; left: 295px \" >\n" +
                "</div>\n" +
                "<div onclick=\"showDiv5()\">" +
                "<img src=\"./nuget.png\" alt=\"ciao\" width=\"150px\" height=\"150px\" style=\"position: absolute;top: 1020px; left: 715px \" >\n" +
                "</div>\n" +

                "</body>\n" +
                "</html>\n" +
                "\n" +
                "\n";

        webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", null);

        //webView.loadUrl( "file:///android_asset/mappa.html" );
        //webView.loadData(html,"text/html", "UTF-8");
/*        PhotoView mappa=(PhotoView) findViewById(R.id.imageView4);
        mappa.setImageResource(R.drawable.mappa);
*/



        //SubsamplingScaleImageView imageView = (SubsamplingScaleImageView)findViewById(R.id.imageView4);
        //imageView.setImage(ImageSource.resource(R.drawable.mappa));

        Random r = new Random();
        int i1 = r.nextInt(3);
        numero_random=i1;
        ordine=i1;
        Integer[] caso=new Integer[]{};
        if (i1==0){caso = new Integer[]{R.drawable.banner1, R.drawable.banner3, R.drawable.banner2};}
        if (i1==1){caso= new Integer[]{R.drawable.banner2, R.drawable.banner1, R.drawable.banner3};}
        if (i1==2){caso= new Integer[]{R.drawable.banner3, R.drawable.banner2, R.drawable.banner1};}
        XMEN =caso;

        for(int i=0;i<XMEN.length;i++) {

            XMENArray.add(XMEN[i]);

        }

        viewPager = (ViewPager) findViewById(R.id.pager_banner);
        banner = getResources().getConfiguration().densityDpi;
        viewPager.setAdapter(new SlideAdapter_Sponsor_banner(MainActivity.this, XMENArray));
        currentPage = 0;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);

            }
        };

            if(swipeTimer!=null){swipeTimer.purge();swipeTimer.cancel();swipeTimer=null;}
            swipeTimer = new Timer();

            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 1000, 6000);

    }

    public void fuori(View view) {
        Intent refresh = new Intent(this, Fuori.class);
        startActivity(refresh);
        swipeTimer.cancel();
        swipeTimer.purge();
        numero_random=-1;
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);

    }

    public void link(View view) {
        String url = "http://www.rossgar.it/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(0);

                    } else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);


                    }
                }
            });

        }
    }

    public void setUpAdapter() {

        LinkedHashMap<String, String[]> thirdLevelq1;
        LinkedHashMap<String, String[]> thirdLevelq2;
        LinkedHashMap<String, String[]> thirdLevelq3;
        /**
         * Second level array list
         */
        List<String[]> secondLevel = new ArrayList<>();
        /**
         * Inner level data
         */
        List<LinkedHashMap<String, String[]>> data = new ArrayList<>();
        String[] ciao={"Ristoranti","Panifici","Rosticceria"};
        String[] ciao_1={"Bed & Breakfast"};
        String[] ciao_2={"Cancelle","Mastacciole"};

        String[] ciao2={"3","4","5"};
        String[] ciao3={"6","7","8"};
        String[] ciao4={"9","10","11"};
        String[] ciao5={"12","13","14"};
        String[] ciao6={"15","16","17"};

        thirdLevelq1 = new LinkedHashMap<>();
        thirdLevelq1.put("padrepio", ciao2);
        thirdLevelq1.put("padrepio2", ciao3);
        thirdLevelq1.put("padrepio3", ciao4);
        thirdLevelq2 = new LinkedHashMap<>();
        thirdLevelq2.put("padrepio", ciao5);
        thirdLevelq3 = new LinkedHashMap<>();


        ArrayList<String> ParentString= new ArrayList<String>();
        ParentString.add("Dove Mangiare");
        ParentString.add("Dove Dormire");
        ParentString.add("Prodotti Tipici");

        secondLevel.add(ciao);
        secondLevel.add(ciao_1);
        secondLevel.add(ciao_2);
        data.add(thirdLevelq1);
        data.add(thirdLevelq2);
        //data.add(thirdLevelq3);

        final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.navigationmenu);
        //passing three level of information to constructor
        ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(this, ParentString, secondLevel, data);
        expandableListView.setAdapter(threeLevelListAdapterAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });


    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Conferma")
                    .setMessage("Sei sicuro di voler uscire?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                finishAffinity();
                            }
                            int pid = Process.myPid();
                            Process.killProcess(pid);
                            MainActivity.super.onBackPressed();

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.web) {
            String url = "http://www.comune.irsina.mt.it";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void cambioLingua(View view) {
        swipeTimer.cancel();
        swipeTimer.purge();

        Locale current = getResources().getConfiguration().locale;

        if(current.getLanguage().equals("it")) {
            Locale myLocale = new Locale("en");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);

            Intent refresh = new Intent(this, MainActivity.class);
            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(refresh);
                finish();
            return;
        }

        if(current.getLanguage().equals("en")) {
            Locale myLocale = new Locale("fr");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);

            Intent refresh = new Intent(this, MainActivity.class);
            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(refresh);
            finish();

            return;
        }
        if(current.getLanguage().equals("fr")) {
            Locale myLocale = new Locale("it");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);

            Intent refresh = new Intent(this, MainActivity.class);
            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(refresh);
            finish();

            return;
        }

    }
    public  boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                return true;
            } else {

                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error","You already have the permission");
            return true;
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }
    public void download(View v) {
        if(haveStoragePermission()==true) {
            if(isNetworkAvailable()==true) {
/*
                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("http://fire94.altervista.org/Images/download.php");
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.allowScanningByMediaScanner();
                request.setMimeType("application/jpeg");
                request.setDestinationInExternalPublicDir("/Download", "giro.jpeg");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);*/

                String url="http://fire94.altervista.org/Images/giro.jpeg";
                Uri uri = Uri.parse(url);
                DownloadManager.Request r = new DownloadManager.Request(uri);

                String fileName = url.substring( url.lastIndexOf('/')+ 1, url.length() );

                // This put the download in the same Download dir the browser uses
                r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
                r.allowScanningByMediaScanner();

                // Notify user when download is completed
                // (Seems to be available since Honeycomb only)
                r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                // Start download
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(r);


                BroadcastReceiver onComplete=new BroadcastReceiver() {
                    public void onReceive(Context ctxt, Intent intent) {

                        Toast.makeText(MainActivity.this, "Image Downloaded", Toast.LENGTH_SHORT).show();
                    }
                };
                registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


                Toast.makeText(MainActivity.this, "Download Started...", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "Connessione Internet Assente", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    protected void onResume() {
        numero_random_fuori=-1;
        numero_random=ordine;
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();

        MainActivity.swipeTimer.cancel();
        MainActivity.swipeTimer.purge();
        swipeTimer=null;
        swipeTimer = new Timer();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);

            }
        };
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, 6000);

        Locale current = getResources().getConfiguration().locale;
        ImageView ib;
        if(cacca_selezionata_iniziale ==1) {
            ib = (ImageView) findViewById(R.id.cambio);
        }
        else {
            ib = (ImageView) findViewById(R.id.cambio_2);
        }
        TextView scegli;
        if(cacca_selezionata_iniziale ==1) {
            scegli=(TextView)findViewById(R.id.textView14);
        }
        else {
            scegli=(TextView)findViewById(R.id.textView14_2);
        }

        TextView cambia;
        if(cacca_selezionata_iniziale ==1) {
            cambia=(TextView)findViewById(R.id.textView13);
        }
        else {
            cambia=(TextView)findViewById(R.id.textView13_2);
        }
        if (current.getLanguage().equals("en")) {
            ib.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.flag_unionjack));
            scegli.setText(R.string.scegli);
            cambia.setText(R.string.cambio);
        }
        if (current.getLanguage().equals("it")) {
            ib.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.flag_italy));
            scegli.setText(R.string.scegli);
            cambia.setText(R.string.cambio);
        }
        if (current.getLanguage().equals("fr")) {
            ib.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.flag_france));
            scegli.setText(R.string.scegli);
            cambia.setText(R.string.cambio);
        }
        deleteCache(getApplicationContext());
        clearApplicationData();
        super.onResume();
    }
    public void clearApplicationData() {
        File cacheDirectory = getCacheDir();
        File applicationDirectory = new File(cacheDirectory.getParent());
        if (applicationDirectory.exists()) {
            String[] fileNames = applicationDirectory.list();
            for (String fileName : fileNames) {
                if (!fileName.equals("lib")) {
                    deleteFile(new File(applicationDirectory, fileName));
                }
            }
        }
    }

    public static boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (file != null) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
                }
            } else {
                deletedAll = file.delete();
            }
        }

        return deletedAll;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        releaseWebView();
    }
    private void releaseWebView() {

        if(webView != null){
            webView.setTag(null);
            webView.clearHistory();
            webView.removeAllViews();
            webView.clearView();
            webView.destroy();
            webView = null;
        }
    }
}

