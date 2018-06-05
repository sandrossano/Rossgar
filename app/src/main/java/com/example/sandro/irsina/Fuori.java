package com.example.sandro.irsina;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.sandro.irsina.MainActivity.banner;
import static com.example.sandro.irsina.MainActivity.height_device;
import static com.example.sandro.irsina.MainActivity.swipeTimer;


/**
 * Created by sandro on 21/05/18.
 */

public class Fuori extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    WebView webView;
    static ViewPager viewPager2;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    int currentPage2 = 0;
    static Timer swipeTimer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.include_fuori).setVisibility(View.VISIBLE);
        findViewById(R.id.include_cattedrale).setVisibility(View.GONE);
        findViewById(R.id.include_main).setVisibility(View.GONE);
        findViewById(R.id.include_catt_storia).setVisibility(View.GONE);
        findViewById(R.id.include_catt_mantegna).setVisibility(View.GONE);
        findViewById(R.id.include_catt_vedere).setVisibility(View.GONE);
        findViewById(R.id.include_catt_miglionico).setVisibility(View.GONE);
        findViewById(R.id.include_catt_miglionico).setVisibility(View.GONE);
        findViewById(R.id.include_sanfrancesco).setVisibility(View.GONE);
        findViewById(R.id.include_museo).setVisibility(View.GONE);
        findViewById(R.id.include_porticella).setVisibility(View.GONE);
        findViewById(R.id.include_muretto).setVisibility(View.GONE);
        findViewById(R.id.include_nuget).setVisibility(View.GONE);
        setTitle("Fuori Mappa");

        Log.d("lingua",Locale.getDefault().getLanguage());
        Log.d("linguaDisplay",Locale.getDefault().getDisplayLanguage());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;


        webView = (WebView)findViewById( R.id.webfuori );

        webView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        //webView.setInitialScale( 1 );
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.getSettings().setSupportZoom( true );
        webView.getSettings().setLoadWithOverviewMode( true ); // Zoom farthest out
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webView.getSettings().setUseWideViewPort( true );
        //webView.getSettings().setBuiltInZoomControls( true );
        webView.setInitialScale(1);

        //webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.scrollTo(0,(webView.getHeight())-10);
            }


            @SuppressLint("NewApi")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String[] a=url.split("attrazione=");
                if(a[1].equals("bottini")) {
                    /*Intent intent = new Intent(Fuori.this, MainActivity.class);
                    startActivity(intent);
                    swipeTimer2.cancel();
                    swipeTimer2.purge();
                    finish();*/
                    Toast.makeText(Fuori.this, "bott", Toast.LENGTH_SHORT).show();
                }
                if(a[1].equals("fontane")) {
                    /*Intent intent = new Intent(Fuori.this, MainActivity.class);
                    startActivity(intent);
                    swipeTimer2.cancel();
                    swipeTimer2.purge();
                    finish();*/
                    Toast.makeText(Fuori.this, "fontane", Toast.LENGTH_SHORT).show();
                }
                if(a[1].equals("peschiera")) {
                    /*Intent intent = new Intent(Fuori.this, MainActivity.class);
                    startActivity(intent);
                    swipeTimer2.cancel();
                    swipeTimer2.purge();
                    finish();*/
                    Toast.makeText(Fuori.this, "peschiera", Toast.LENGTH_SHORT).show();
                }
                if(a[1].equals("pieta")) {
                    /*Intent intent = new Intent(Fuori.this, MainActivity.class);
                    startActivity(intent);
                    swipeTimer2.cancel();
                    swipeTimer2.purge();
                    finish();*/
                    Toast.makeText(Fuori.this, "pietà", Toast.LENGTH_SHORT).show();
                }
                if(a[1].equals("juso")) {
                    /*Intent intent = new Intent(Fuori.this, MainActivity.class);
                    startActivity(intent);
                    swipeTimer2.cancel();
                    swipeTimer2.purge();
                    finish();*/
                    Toast.makeText(Fuori.this, "madonna dello Juso", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


        String html="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title>Fuori</title>\n" +
                "</head>\n" +
                "<body style=\"background-color:#dfd1b0;\">\n" +
                "\n" +
                "<img src=\"./fuori.png\" width=\"1500px\" heigth=\"3000px\" alt=\"mapppa\">\n" +
                "<a style=\"text-decoration: none;\" href=\"https://www.google.it/attrazione=bottini\">" +
                "<div >\n" +
                "\t<img src=\"./banner1.png\" alt=\"ciao\" width=\"395px\" height=\"190px\" style=\"position: absolute;top: 1155px; left: 50px;opacity:0;\" >\n" +
                "</div></a>\n" +
                "<a style=\"text-decoration: none;\" href=\"https://www.google.it/attrazione=fontane\">" +
                "<div >\n" +
                "\t<img src=\"./banner1.png\" alt=\"ciao\" width=\"410px\" height=\"190px\" style=\"position: absolute;top: 1375px; left: 100px;opacity:0;\" >\n" +
                "</div></a>\n" +
                "<a style=\"text-decoration: none;\" href=\"https://www.google.it/attrazione=peschiera\">" +
                "<div >\n" +
                "\t<img src=\"./banner1.png\" alt=\"ciao\" width=\"410px\" height=\"180px\" style=\"position: absolute;top: 1585px; left: 600px;opacity:0;\" >\n" +
                "</div></a>\n" +
                "<a style=\"text-decoration: none;\" href=\"https://www.google.it/attrazione=pieta\">" +
                "<div >\n" +
                "\t<img src=\"./banner1.png\" alt=\"ciao\" width=\"410px\" height=\"180px\" style=\"position: absolute;top: 1825px; left: 530px;opacity:0;\" >\n" +
                "</div></a>\n" +
                "<a style=\"text-decoration: none;\" href=\"https://www.google.it/attrazione=juso\">" +
                "<div >\n" +
                "\t<img src=\"./banner1.png\" alt=\"ciao\" width=\"485px\" height=\"215px\" style=\"position: absolute;top: 1175px; left: 990px;opacity:0;\" >\n" +
                "</div></a>\n" +
                "</body>\n" +
                "</html>\n" +
                "\n" +
                "\n";

        webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", null);



        /*RelativeLayout rl = (RelativeLayout) findViewById(R.id.bottoni);

        RelativeLayout.LayoutParams Params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        Params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        Params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        Params.leftMargin = 0;
        Params.topMargin = (int) 750;
        Log.d("width",width+"");Log.d("heigth",height+"");
        Button newButton = new Button(this);
        if(rl==null){
            Log.d("ciao","button");}
        if (rl != null) {
            rl.addView(newButton, Params);
        }
*/



        ImageView ib=(ImageView)findViewById(R.id.cambiofuori);
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

        final Integer[] XMEN = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};

        for(int i=0;i<XMEN.length;i++) {

            XMENArray.add(XMEN[i]);

        }

        viewPager2 = (ViewPager) findViewById(R.id.pager_banner_fuori);

        viewPager2.setAdapter(new SlideAdapter_Sponsor_banner(Fuori.this, XMENArray));
        currentPage2 = 0;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage2 == XMEN.length) {
                    currentPage2 = 0;
                }
                viewPager2.setCurrentItem(currentPage2++, true);

            }
        };


        swipeTimer2 = new Timer();

        swipeTimer2.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, 6000);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        swipeTimer2.cancel();
        swipeTimer2.purge();
        Intent refresh = new Intent(this, MainActivity.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(refresh);
        finishAffinity();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void cambioLingua(View view) {
        Locale current = getResources().getConfiguration().locale;
        swipeTimer2.cancel();
        swipeTimer2.purge();
        if(current.getLanguage().equals("it")) {
            Locale myLocale = new Locale("en");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);

            Intent refresh = new Intent(this, Fuori.class);
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

            Intent refresh = new Intent(this, Fuori.class);
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

            Intent refresh = new Intent(this, Fuori.class);
            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(refresh);
            finish();
            return;
        }

    }

    public void bottini(View view) {
        Toast.makeText(Fuori.this, "Bottini", Toast.LENGTH_SHORT).show();
    }
    public void peschiera(View view) {
        Toast.makeText(Fuori.this, "Peschiera", Toast.LENGTH_SHORT).show();
    }
    public void chiesapieta(View view) {
        Toast.makeText(Fuori.this, "Chiesa della Pietà", Toast.LENGTH_SHORT).show();
    }
    public void juso(View view) {
        Toast.makeText(Fuori.this, "Madonna dello Juso", Toast.LENGTH_SHORT).show();
    }

    public void fontane(View view) {
        Toast.makeText(Fuori.this, "Fontane", Toast.LENGTH_SHORT).show();
    }
}
