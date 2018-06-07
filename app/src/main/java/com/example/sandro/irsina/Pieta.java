package com.example.sandro.irsina;

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

/**
 * Created by sandro on 21/05/18.
 */

public class Pieta extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private int currentPage = 0;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.include_juso).setVisibility(View.GONE);
        findViewById(R.id.include_pieta).setVisibility(View.VISIBLE);
        findViewById(R.id.include_peschiera).setVisibility(View.GONE);
        findViewById(R.id.include_fontane).setVisibility(View.GONE);
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
        setTitle(R.string.pieta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        init();
    }

    private void init() {

        final Integer[] XMEN = {R.drawable.pieta1};

        for(int i=0;i<XMEN.length;i++) {

               XMENArray.add(XMEN[i]);

        }
        viewPager = (ViewPager) findViewById(R.id.pager_pieta);
        viewPager.setAdapter(new SlideAdapter(Pieta.this, XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator_pieta);
        indicator.setViewPager(viewPager);

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
            Pieta.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
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
        Intent refresh = new Intent(this, Fuori.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(refresh);
        finishAffinity();
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

            Intent refresh = new Intent(this, Pieta.class);
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

            Intent refresh = new Intent(this, Pieta.class);
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

            Intent refresh = new Intent(this, Pieta.class);
            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(refresh);
            finish();
            return;
        }
    }
    public void maps(View view){
        //placeid: https://developers.google.com/places/place-id
        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=Chiesa+della+Pietà,+Via+della+Pietá,+Irsina,+MT");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
}

