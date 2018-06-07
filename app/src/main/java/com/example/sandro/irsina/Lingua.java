package com.example.sandro.irsina;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class Lingua extends AppCompatActivity {
    int pronto=0;
    SharedPreferences prefs = null;
    ProgressBar progressBar;

    @SuppressLint({"ResourceAsColor", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lingua);
        final TextView continua=(TextView)findViewById(R.id.textView21);



        progressBar = (ProgressBar) findViewById(R.id.progressBar4);
        DrawableCompat.setTint(progressBar.getIndeterminateDrawable(), Color.rgb(255,153,0));

        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorAccent));

        prefs = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(pronto==0) {
                    //continua.setVisibility(View.VISIBLE);
                    Intent refresh = new Intent(getApplicationContext(), MainActivity.class);
                    refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(refresh);
                    deleteCache(getApplicationContext());
                }

            }
        },5500);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            pronto=1;
            AlertDialog alertDialog = new AlertDialog.Builder(Lingua.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Alert message to be shown");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            progressBar.setVisibility(View.GONE);
                            //continua.setVisibility(View.VISIBLE);
                            Intent refresh = new Intent(getApplicationContext(), MainActivity.class);
                            refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(refresh);
                        }
                    });
            alertDialog.show();



            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }


    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    public void francese(View view) {
        //cambio lingua

        Locale myLocale = new Locale("fr");
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        TextView continua=(TextView)findViewById(R.id.textView21);
        continua.setVisibility(View.VISIBLE);

        //per pagina aggiona lingua
        /*Intent refresh = new Intent(this, Lingua.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(refresh);
        finish();*/
        Intent refresh2 = new Intent(this, Lingua.class);
        refresh2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(refresh2);
        finish();
        return;

    }
    public void italiano(View view) {
        //cambio lingua
        Locale myLocale = new Locale("it");
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        TextView continua=(TextView)findViewById(R.id.textView21);
        continua.setVisibility(View.VISIBLE);

        //per pagina aggiona lingua
        /*Intent refresh = new Intent(this, Lingua.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(refresh);
        finish();*/
        Intent refresh = new Intent(this, Lingua.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(refresh);
        finish();
        return;
    }
    public void inglese(View view) {
        //cambio lingua
        Locale myLocale = new Locale("en");
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        TextView continua=(TextView)findViewById(R.id.textView21);
        continua.setVisibility(View.VISIBLE);

        //per pagina aggiona lingua
        /*Intent refresh = new Intent(this, Lingua.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(refresh);
        finish();*/
        Intent refresh = new Intent(this, Lingua.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(refresh);
        finish();
        return;
    }

    public void continua(View view) {
        Intent refresh = new Intent(this, MainActivity.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(refresh);
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}
