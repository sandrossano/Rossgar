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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.sandro.irsina.Lingua.logValue;
import static com.example.sandro.irsina.MainActivity.numero_random;
import static com.example.sandro.irsina.Lingua.deleteCache;
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
    static Integer[] XMEN2;
    int cacca_selezionata_iniziale=0;

    public boolean hasNavBar (Resources resources)
    {
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        return id > 0 && resources.getBoolean(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        logValue();
            System.runFinalization();
            Runtime.getRuntime().gc();
            System.gc();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        if(hasNavBar(getResources())==true){
            findViewById(R.id.include_fuori).setVisibility(View.VISIBLE);
            findViewById(R.id.include_fuori_2).setEnabled(false);
            findViewById(R.id.include_fuori_2).setVisibility(View.GONE);
        }
        else{
            findViewById(R.id.include_fuori).setVisibility(View.GONE);
            findViewById(R.id.include_fuori).setEnabled(false);
            findViewById(R.id.include_fuori_2).setVisibility(View.VISIBLE);
        }


        if (findViewById(R.id.include_fuori_2).getVisibility()==View.GONE){cacca_selezionata_iniziale=1;}
        else{cacca_selezionata_iniziale=2;}

        deleteCache(getApplicationContext());
clearApplicationData();
        Log.d("lingua",Locale.getDefault().getLanguage());
        Log.d("linguaDisplay",Locale.getDefault().getDisplayLanguage());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackground(getResources().getDrawable(R.drawable.color_back));
        setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer.removeView(navigationView);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;


        if (cacca_selezionata_iniziale==1){
            webView = (WebView)findViewById( R.id.webfuori );}
        else{
            webView = (WebView)findViewById( R.id.webfuori_2 );
        }


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

        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        //webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        final int finalCacca_selezionata_iniziale = cacca_selezionata_iniziale;
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.scrollTo(0,(webView.getHeight()));
                ImageView imm;
                if(finalCacca_selezionata_iniziale ==1) {
                    imm = (ImageView) findViewById(R.id.logo_webmain2);
                }
                else {
                    imm = (ImageView) findViewById(R.id.logo_webmain2_2);
                }
                imm.setVisibility(View.GONE);
                imm.destroyDrawingCache();
                webView.setVisibility(View.VISIBLE);
            }


            @SuppressLint("NewApi")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String[] a=url.split("attrazione=");
                if(a[1].equals("bottini")) {
                    Intent intent = new Intent(Fuori.this, Bottini.class);
                    startActivity(intent);
                    swipeTimer2.cancel();
                    swipeTimer2.purge();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                    //Toast.makeText(Fuori.this, "bott", Toast.LENGTH_SHORT).show();
                }
                if(a[1].equals("fontane")) {
                    Intent intent = new Intent(Fuori.this, Fontane.class);
                    startActivity(intent);
                    swipeTimer2.cancel();
                    swipeTimer2.purge();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                    //Toast.makeText(Fuori.this, "fontane", Toast.LENGTH_SHORT).show();
                }
                if(a[1].equals("peschiera")) {
                    Intent intent = new Intent(Fuori.this, Peschiera.class);
                    startActivity(intent);
                    swipeTimer2.cancel();
                    swipeTimer2.purge();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                    //Toast.makeText(Fuori.this, "peschiera", Toast.LENGTH_SHORT).show();
                }
                if(a[1].equals("pieta")) {
                    Intent intent = new Intent(Fuori.this, Pieta.class);
                    startActivity(intent);
                    swipeTimer2.cancel();
                    swipeTimer2.purge();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                    //Toast.makeText(Fuori.this, "pietà", Toast.LENGTH_SHORT).show();
                }
                if(a[1].equals("juso")) {
                    Intent intent = new Intent(Fuori.this, Juso.class);
                    startActivity(intent);
                    swipeTimer2.cancel();
                    swipeTimer2.purge();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                    //Toast.makeText(Fuori.this, "madonna dello Juso", Toast.LENGTH_SHORT).show();
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



        ImageView ib;
        if (cacca_selezionata_iniziale==1){
            ib=(ImageView)findViewById(R.id.cambiofuori);}
        else{ib=(ImageView)findViewById(R.id.cambiofuori_2);}
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

        Random r = new Random();
        int i1 = r.nextInt(3);
        numero_random=i1;
        Integer[] caso=new Integer[]{};
        if (i1==0){caso = new Integer[]{R.drawable.banner1, R.drawable.banner3, R.drawable.banner2};}
        if (i1==1){caso= new Integer[]{R.drawable.banner2, R.drawable.banner1, R.drawable.banner3};}
        if (i1==2){caso= new Integer[]{R.drawable.banner3, R.drawable.banner2, R.drawable.banner1};}
        XMEN2 =caso;
        for(int i=0;i<XMEN2.length;i++) {

            XMENArray.add(XMEN2[i]);

        }

        viewPager2 = (ViewPager) findViewById(R.id.pager_banner_fuori);

        viewPager2.setAdapter(new SlideAdapter_Sponsor_banner(Fuori.this, XMENArray));
        currentPage2 = 0;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage2 == XMEN2.length) {
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

        overridePendingTransition(R.anim.fadein_back,R.anim.fadeout_back);

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
    @Override
    protected void onResume() {

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();

        deleteCache(getApplicationContext());
        clearApplicationData();

        swipeTimer2.cancel();
        swipeTimer2.purge();

        swipeTimer2 = new Timer();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage2 == XMEN2.length) {
                    currentPage2 = 0;
                }
                viewPager2.setCurrentItem(currentPage2++, true);

            }
        };
        swipeTimer2.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, 6000);

        Locale current = getResources().getConfiguration().locale;
        ImageView ib;
        if(cacca_selezionata_iniziale ==1) {
            ib = (ImageView) findViewById(R.id.cambiofuori);
        }
        else {
            ib = (ImageView) findViewById(R.id.cambiofuori_2);
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
            cambia.setText(R.string.cambio);
        }
        if (current.getLanguage().equals("it")) {
            ib.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.flag_italy));
            cambia.setText(R.string.cambio);
        }
        if (current.getLanguage().equals("fr")) {
            ib.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.flag_france));
            cambia.setText(R.string.cambio);
        }
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
