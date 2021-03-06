package com.irsina.sandro.irsina;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static com.irsina.sandro.irsina.Lingua.deleteCache;
import static com.irsina.sandro.irsina.Lingua.logValue;

/**
 * Created by sandro on 21/05/18.
 */

public class Fontane extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private int currentPage = 0;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logValue();

        findViewById(R.id.include_juso).setVisibility(View.GONE);
        findViewById(R.id.include_pieta).setVisibility(View.GONE);
        findViewById(R.id.include_peschiera).setVisibility(View.GONE);
        findViewById(R.id.include_fontane).setVisibility(View.VISIBLE);
        findViewById(R.id.include_bottini).setVisibility(View.GONE);
        findViewById(R.id.include_fuori).setVisibility(View.GONE);
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
        setTitle(R.string.fontane);

        deleteCache(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        init();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer.removeView(navigationView);

    }

    private void init() {

        final Integer[] XMEN = {R.drawable.fontana,R.drawable.fontane2,R.drawable.fontana1};

        for(int i=0;i<XMEN.length;i++) {

               XMENArray.add(XMEN[i]);

        }
        viewPager = (ViewPager) findViewById(R.id.pager_fontane);
        viewPager.setAdapter(new SlideAdapter(Fontane.this, XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator_fontane);
        indicator.setViewPager(viewPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (viewPager.getCurrentItem()+1 == XMEN.length) {
                    viewPager.setCurrentItem(0);
                    return;
                }
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1, true);

            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 6000);
    }


    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            Fontane.this.runOnUiThread(new Runnable() {
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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Locale current = getResources().getConfiguration().locale;
        if(current.getLanguage().equals("en")) {
            getMenuInflater().inflate(R.menu.main_pagina_en, menu);
        }
        if(current.getLanguage().equals("it")) {
            getMenuInflater().inflate(R.menu.main_pagina_it, menu);
        }
        if(current.getLanguage().equals("fr")) {
            getMenuInflater().inflate(R.menu.main_pagina_fr, menu);
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.web) {
            cambioLingua();
        }


        return super.onOptionsItemSelected(item);
    }

    public void cambioLingua() {
        Locale current = getResources().getConfiguration().locale;

        if (current.getLanguage().equals("it")) {
            Locale myLocale = new Locale("en");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);

            Intent refresh = new Intent(this, Fontane.class);
            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(refresh);
            finish();
            return;
        }

        if (current.getLanguage().equals("en")) {
            Locale myLocale = new Locale("fr");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);

            Intent refresh = new Intent(this, Fontane.class);
            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(refresh);
            finish();
            return;
        }
        if (current.getLanguage().equals("fr")) {
            Locale myLocale = new Locale("it");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);

            Intent refresh = new Intent(this, Fontane.class);
            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(refresh);
            finish();
            return;
        }
    }
    public void maps(View view){
        //placeid: https://developers.google.com/places/place-id
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:40.747035, 16.245359?q=40.747035, 16.245359(12 Fontane)"));
        startActivity(intent);

    }
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewPager.destroyDrawingCache();
        viewPager.removeAllViews();
    }
}

