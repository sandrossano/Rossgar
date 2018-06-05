package com.example.sandro.irsina;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by sandro on 21/05/18.
 */

public class Mantegna extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private int currentPage = 0;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.include_cattedrale).setVisibility(View.GONE);
        findViewById(R.id.include_main).setVisibility(View.GONE);
        findViewById(R.id.include_catt_storia).setVisibility(View.GONE);
        findViewById(R.id.include_catt_mantegna).setVisibility(View.VISIBLE);
        findViewById(R.id.include_catt_miglionico).setVisibility(View.GONE);
        findViewById(R.id.include_museo).setVisibility(View.GONE);
        findViewById(R.id.include_porticella).setVisibility(View.GONE);
        findViewById(R.id.include_muretto).setVisibility(View.GONE);
        findViewById(R.id.include_fuori).setVisibility(View.GONE);
        setTitle(R.string.statua);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        init();
    }

    private void init() {

        final Integer[] XMEN = {R.drawable.flag_france, R.drawable.flag_italy, R.drawable.flag_unionjack};

        for(int i=0;i<XMEN.length;i++) {

               XMENArray.add(XMEN[i]);

        }
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SlideAdapter(Mantegna.this, XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
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
            Mantegna.this.runOnUiThread(new Runnable() {
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

}

