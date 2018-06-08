package com.example.sandro.irsina;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by sandro on 21/05/18.
 */

public class Cancelle extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String sede ="";
    String indirizzo="";
    String email = "";
    String telefono_fisso = "";
    ViewPager viewPager;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.include_cancelle).setVisibility(View.VISIBLE);
        findViewById(R.id.include_cattedrale).setVisibility(View.GONE);
        findViewById(R.id.include_main).setVisibility(View.GONE);
        findViewById(R.id.include_catt_storia).setVisibility(View.GONE);
        findViewById(R.id.include_catt_mantegna).setVisibility(View.GONE);
        findViewById(R.id.include_catt_vedere).setVisibility(View.GONE);
        findViewById(R.id.include_catt_miglionico).setVisibility(View.GONE);
        findViewById(R.id.include_catt_miglionico).setVisibility(View.GONE);
        findViewById(R.id.include_sanfrancesco).setVisibility(View.GONE);
        findViewById(R.id.include_fuori).setVisibility(View.GONE);
        findViewById(R.id.include_sponsor).setVisibility(View.GONE);
        findViewById(R.id.include_nuget).setVisibility(View.GONE);
        setTitle(R.string.cancelle);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


        ImageView imm=(ImageView) findViewById(R.id.immagineSingolo);

        String nomesing="";
        String fotosing="";


        //BitmapDrawable d=writeOnDrawable(R.drawable.flag_italy,"Nome Società");
        //imm.setImageBitmap(d.getBitmap());
        //imm.setImageResource(R.drawable.flag_italy);


        TextView wow1= (TextView) findViewById(R.id.tvNumber1);
        wow1.setText(telefono_fisso);
        TextView wow2= (TextView) findViewById(R.id.tvNumber3);
        wow2.setText(email);
        TextView wow5= (TextView) findViewById(R.id.tvNumber5);
        wow5.setText(sede);
        TextView wow4= (TextView) findViewById(R.id.tvNumber4);
        wow4.setText(indirizzo);

        final Integer[] XMEN = {R.drawable.flag_france, R.drawable.flag_italy, R.drawable.flag_unionjack};

        for(int i=0;i<XMEN.length;i++) {

            XMENArray.add(XMEN[i]);

        }

        viewPager = (ViewPager) findViewById(R.id.pager_tipici);
        viewPager.setAdapter(new SlideAdapter_Sponsor(Cancelle.this, XMENArray));

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
        }, 500, 4000);
    }


    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            Cancelle.this.runOnUiThread(new Runnable() {
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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void chiamata(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String str=telefono_fisso.replace("/","");
        str=str.replace("-","");
        intent.setData(Uri.parse("tel:"+str));
        startActivity(Intent.createChooser(intent, "Chiama..."));
    }

    public void email(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }

    public BitmapDrawable writeOnDrawable(int drawableId, String text){

        Bitmap bm = BitmapFactory.decodeResource(getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(35);

        Canvas canvas = new Canvas(bm);
        canvas.drawText(text, 10, bm.getHeight()-30, paint);

        return new BitmapDrawable(bm);
    }
}