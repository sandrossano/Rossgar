package com.example.sandro.irsina;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Locale;

import static com.example.sandro.irsina.Lingua.deleteCache;
import static com.example.sandro.irsina.Lingua.logValue;


/**
 * Created by sandro on 21/05/18.
 */

public class SanFrancesco extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        logValue();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.include_cattedrale).setVisibility(View.GONE);
        findViewById(R.id.include_main).setVisibility(View.GONE);
        findViewById(R.id.include_catt_storia).setVisibility(View.GONE);
        findViewById(R.id.include_catt_mantegna).setVisibility(View.GONE);
        findViewById(R.id.include_catt_vedere).setVisibility(View.GONE);
        findViewById(R.id.include_catt_miglionico).setVisibility(View.GONE);
        findViewById(R.id.include_catt_miglionico).setVisibility(View.GONE);
        findViewById(R.id.include_sanfrancesco).setVisibility(View.VISIBLE);
        findViewById(R.id.include_museo).setVisibility(View.GONE);
        findViewById(R.id.include_porticella).setVisibility(View.GONE);
        findViewById(R.id.include_muretto).setVisibility(View.GONE);
        findViewById(R.id.include_fuori).setVisibility(View.GONE);
        findViewById(R.id.include_nuget).setVisibility(View.GONE);
        setTitle("San Francesco D'Assisi");

        deleteCache(getApplicationContext());
clearApplicationData();
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
        Log.d("lingua",Locale.getDefault().getLanguage());
        Log.d("linguaDisplay",Locale.getDefault().getDisplayLanguage());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


            ImageView ib=(ImageView)findViewById(R.id.cambiol);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer.removeView(navigationView);

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

        if(current.getLanguage().equals("it")) {
            Locale myLocale = new Locale("en");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);

            Intent refresh = new Intent(this, SanFrancesco.class);
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

            Intent refresh = new Intent(this, SanFrancesco.class);
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

            Intent refresh = new Intent(this, SanFrancesco.class);
            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(refresh);
            finish();
            return;
        }

    }

    public void apri_storia_sanfrancesco(View view) {
        Intent refresh = new Intent(this, Storia_SanFrancesco.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(refresh);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        return;
    }

    public void cripta(View view) {
        Intent refresh = new Intent(this, CriptaFrancesco.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(refresh);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);

        return;
    }
    public void maps(View view){
        //placeid: https://developers.google.com/places/place-id
        //Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=Chiesa%20San%20Francesco%20D'Assisi&query_place_id=ChIJ36s3iSGKOBMRVF5FH4HAiVE");
        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=Chiesa+San+Francesco+D'Assisi,+Irsina");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }

    }

    @Override
    protected void onResume() {

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();

        deleteCache(getApplicationContext());
        clearApplicationData();

        Locale current = getResources().getConfiguration().locale;
        ImageView ib = (ImageView) findViewById(R.id.cambiol);
        TextView cripta=(TextView)findViewById(R.id.text_sancripta);
        TextView orari=(TextView)findViewById(R.id.text_sanorari);
        TextView storia=(TextView)findViewById(R.id.text_sanstoria);
        if (current.getLanguage().equals("en")) {
            ib.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.flag_unionjack));
            cripta.setText(R.string.cripta);
            orari.setText(R.string.orari);
            storia.setText(R.string.storia);
        }
        if (current.getLanguage().equals("it")) {
            ib.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.flag_italy));
            cripta.setText(R.string.cripta);
            orari.setText(R.string.orari);
            storia.setText(R.string.storia);
        }
        if (current.getLanguage().equals("fr")) {
            ib.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.flag_france));
            cripta.setText(R.string.cripta);
            orari.setText(R.string.orari);
            storia.setText(R.string.storia);
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
}
