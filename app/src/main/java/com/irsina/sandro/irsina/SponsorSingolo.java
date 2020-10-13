package com.irsina.sandro.irsina;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
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
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static com.irsina.sandro.irsina.Lingua.deleteCache;
import static com.irsina.sandro.irsina.Lingua.logValue;
import static com.irsina.sandro.irsina.MainActivity.banner;


/**
 * Created by sandro on 21/05/18.
 */

public class SponsorSingolo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String nome="";
    String sede ="";
    String indirizzo="";
    String email = "";
    String descrizione = "";
    String telefono_fisso = "";
    ViewPager viewPager;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    static int currentPage = 0;
    String orariolun = "";
    String orariomar = "";
    String orariomer = "";
    String orariogio = "";
    String orarioven = "";
    String orariosab = "";
    String orariodom = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        logValue();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.include_cancelle).setVisibility(View.GONE);
        findViewById(R.id.include_cattedrale).setVisibility(View.GONE);
        findViewById(R.id.include_main).setVisibility(View.GONE);
        findViewById(R.id.include_catt_storia).setVisibility(View.GONE);
        findViewById(R.id.include_catt_mantegna).setVisibility(View.GONE);
        findViewById(R.id.include_catt_vedere).setVisibility(View.GONE);
        findViewById(R.id.include_catt_miglionico).setVisibility(View.GONE);
        findViewById(R.id.include_catt_miglionico).setVisibility(View.GONE);
        findViewById(R.id.include_sanfrancesco).setVisibility(View.GONE);
        findViewById(R.id.include_fuori).setVisibility(View.GONE);
        findViewById(R.id.include_sponsor).setVisibility(View.VISIBLE);
        findViewById(R.id.include_nuget).setVisibility(View.GONE);
        nome=getIntent().getStringExtra("nome");
        setTitle(nome);

        deleteCache(getApplicationContext());


        telefono_fisso=getIntent().getStringExtra("numero");
        email=getIntent().getStringExtra("email");
        sede=getIntent().getStringExtra("indirizzo");
        indirizzo=getIntent().getStringExtra("sito");
        descrizione=getIntent().getStringExtra("descrizione");
        orariolun=getIntent().getStringExtra("orariolun");
        orariomar=getIntent().getStringExtra("orariomar");
        orariomer=getIntent().getStringExtra("orariomer");
        orariogio=getIntent().getStringExtra("orariogio");
        orarioven=getIntent().getStringExtra("orarioven");
        orariosab=getIntent().getStringExtra("orariosab");
        orariodom=getIntent().getStringExtra("orariodom");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


            if(indirizzo!=null && indirizzo.equals("")){findViewById(R.id.sito).setVisibility(View.GONE);}
        if(email!=null && email.equals("")){findViewById(R.id.email).setVisibility(View.GONE);}
        if(getIntent().getStringExtra("luogo").equals("Sita") || getIntent().getStringExtra("luogo").equals("Tito")){ TextView orari1=findViewById(R.id.orari1); orari1.setVisibility(View.GONE);
            final TextView orari2=findViewById(R.id.orari2); orari2.setGravity(0); orari2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url=orari2.getText().toString().split("\n")[1];
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(i);
                }
            });
                findViewById(R.id.martedì).setVisibility(View.GONE);
            findViewById(R.id.mercoledì).setVisibility(View.GONE);
            findViewById(R.id.giovedì).setVisibility(View.GONE);
            findViewById(R.id.venerdì).setVisibility(View.GONE);
            findViewById(R.id.sabato).setVisibility(View.GONE);
            findViewById(R.id.domenica).setVisibility(View.GONE);}
        ImageView imm=(ImageView) findViewById(R.id.immagineSingolo);


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
        TextView wow6= (TextView) findViewById(R.id.descrizione1);
        wow6.setText(Html.fromHtml(descrizione));
        TextView wow7= (TextView) findViewById(R.id.orari2);
        wow7.setText(orariolun);
        TextView wow8= (TextView) findViewById(R.id.orari4);
        wow8.setText(orariomar);
        TextView wow9= (TextView) findViewById(R.id.orari6);
        wow9.setText(orariomer);
        TextView wow10= (TextView) findViewById(R.id.orari8);
        wow10.setText(orariogio);
        TextView wow11= (TextView) findViewById(R.id.orari10);
        wow11.setText(orarioven);
        TextView wow12= (TextView) findViewById(R.id.orari12);
        wow12.setText(orariosab);
        TextView wow13= (TextView) findViewById(R.id.orari14);
        wow13.setText(orariodom);

        Integer[] XMEN;
        ImageView logo=findViewById(R.id.imageView24);
        if(getIntent().getStringExtra("luogo").equals("Nugent")){logo.setImageDrawable(getResources().getDrawable(R.drawable.nugent_titolo));logo.getLayoutParams().height = (100*banner)/160;XMEN= new Integer[]{R.drawable.nugent1, R.drawable.nugent2, R.drawable.nugent3, R.drawable.nugent4, R.drawable.nugent5, R.drawable.nugent6, R.drawable.nugent7, R.drawable.nugent8, R.drawable.nugent9, R.drawable.nugent10, R.drawable.nugent11};}
        else{if(getIntent().getStringExtra("luogo").equals("Macelleria")){logo.setImageDrawable(getResources().getDrawable(R.drawable.macelleria));XMEN= new Integer[]{R.drawable.macelleria_slide1, R.drawable.macelleria_slide2, R.drawable.macelleria_slide3,R.drawable.macelleria_slide4,R.drawable.macelleria_slide5,R.drawable.macelleria_slide6,R.drawable.macelleria_slide7,R.drawable.macelleria_slide8,R.drawable.macelleria_slide9,R.drawable.macelleria_slide10,R.drawable.macelleria_slide11,R.drawable.macelleria_slide12,R.drawable.macelleria_slide13};}
            else{
            if(getIntent().getStringExtra("luogo").equals("Contessa")){logo.setImageDrawable(getResources().getDrawable(R.drawable.contessa));XMEN= new Integer[]{R.drawable.contessa1, R.drawable.contessa2, R.drawable.contessa3,R.drawable.contessa4,R.drawable.contessa5,R.drawable.contessa6,R.drawable.contessa7,R.drawable.contessa8,R.drawable.contessa9};}
                else{ if(getIntent().getStringExtra("luogo").equals("Sita")){logo.setImageDrawable(getResources().getDrawable(R.drawable.logo_sita));XMEN= new Integer[]{R.drawable.sita1, R.drawable.sita2, R.drawable.sita3};}
                    else{if(getIntent().getStringExtra("luogo").equals("Lentini")){logo.setImageDrawable(getResources().getDrawable(R.drawable.pickup_titolo));logo.getLayoutParams().height = (250*banner)/160;XMEN= new Integer[]{R.drawable.lentini1, R.drawable.lentini2, R.drawable.lentini3,R.drawable.lentini4};}
                        else{if(getIntent().getStringExtra("luogo").equals("Rodary")){logo.setImageDrawable(getResources().getDrawable(R.drawable.logo_rodary));XMEN= new Integer[]{R.drawable.rodary1, R.drawable.rodary2, R.drawable.rodary3, R.drawable.rodary4, R.drawable.rodary5, R.drawable.rodary6, R.drawable.rodary7, R.drawable.rodary8, R.drawable.rodary9, R.drawable.rodary10};}
                            else{if(getIntent().getStringExtra("luogo").equals("Ducale")){logo.setImageDrawable(getResources().getDrawable(R.drawable.logo_ducale));XMEN= new Integer[]{R.drawable.ducale, R.drawable.ducale1, R.drawable.ducale2, R.drawable.ducale3, R.drawable.ducale4};}
                                else{if(getIntent().getStringExtra("luogo").equals("Fuoco")){logo.setImageDrawable(getResources().getDrawable(R.drawable.logo_ducale));XMEN= new Integer[]{R.drawable.rodary1, R.drawable.rodary2, R.drawable.rodary3, R.drawable.rodary4, R.drawable.rodary5, R.drawable.rodary6, R.drawable.rodary7, R.drawable.rodary8, R.drawable.rodary9, R.drawable.rodary10};}
                                    else{if(getIntent().getStringExtra("luogo").equals("Car")){logo.setImageDrawable(getResources().getDrawable(R.drawable.logo_car));logo.getLayoutParams().height = (100*banner)/160;XMEN= new Integer[]{R.drawable.carservice};}
                                        else{if(getIntent().getStringExtra("luogo").equals("Bottega")){logo.setImageDrawable(getResources().getDrawable(R.drawable.bottega_titolo));logo.getLayoutParams().height = (150*banner)/160;XMEN= new Integer[]{R.drawable.gusto0, R.drawable.gusto1, R.drawable.gusto2, R.drawable.gusto3};}
                                            else{if(getIntent().getStringExtra("luogo").equals("Despar")){logo.setImageDrawable(getResources().getDrawable(R.drawable.despar_titolo));logo.getLayoutParams().height = (150*banner)/160;XMEN= new Integer[]{R.drawable.despar0, R.drawable.despar1, R.drawable.despar2};}
                                                else{if(getIntent().getStringExtra("luogo").equals("Rosticceria Despar")){logo.setImageDrawable(getResources().getDrawable(R.drawable.bottega_titolo));logo.getLayoutParams().height = (150*banner)/160;XMEN= new Integer[]{R.drawable.rodary1, R.drawable.rodary2, R.drawable.rodary3, R.drawable.rodary4, R.drawable.rodary5, R.drawable.rodary6, R.drawable.rodary7, R.drawable.rodary8, R.drawable.rodary9, R.drawable.rodary10};}
                                                    else{if(getIntent().getStringExtra("luogo").equals("Crai")){logo.setImageDrawable(getResources().getDrawable(R.drawable.crai_titolo));XMEN= new Integer[]{R.drawable.crai1, R.drawable.crai2, R.drawable.crai3};}
                                                        else{if(getIntent().getStringExtra("luogo").equals("Vecchio Mulino")){logo.setImageDrawable(getResources().getDrawable(R.drawable.mulino_titolo));XMEN= new Integer[]{R.drawable.mulino_11, R.drawable.mulino_2, R.drawable.mulino_3, R.drawable.mulino_4, R.drawable.mulino_5, R.drawable.mulino_6, R.drawable.mulino_1, R.drawable.mulino_8, R.drawable.mulino_9, R.drawable.mulino_10, R.drawable.mulino_7, R.drawable.mulino_12, R.drawable.mulino_13, R.drawable.mulino_14, R.drawable.mulino_15};}
                                                            else{if(getIntent().getStringExtra("luogo").equals("Spighe")){logo.setImageDrawable(getResources().getDrawable(R.drawable.spighe_testo));logo.getLayoutParams().height = (50*banner)/160;XMEN= new Integer[]{R.drawable.spighe0, R.drawable.spighe1, R.drawable.spighe2, R.drawable.spighe4, R.drawable.spighe5, R.drawable.spighe6};}
                                                                else{if(getIntent().getStringExtra("luogo").equals("Tito")){logo.setImageDrawable(getResources().getDrawable(R.drawable.titobus));XMEN= new Integer[]{R.drawable.tito1, R.drawable.tito2, R.drawable.tito3};}
                                                                    else{if(getIntent().getStringExtra("luogo").equals("Smaldone")){logo.setImageDrawable(getResources().getDrawable(R.drawable.titobus));logo.setVisibility(View.GONE);XMEN= new Integer[]{R.drawable.immaginenondisponibile};findViewById(R.id.numero).setVisibility(View.GONE);findViewById(R.id.indirizzo).setVisibility(View.GONE);findViewById(R.id.lunedì).setVisibility(View.GONE);findViewById(R.id.martedì).setVisibility(View.GONE);findViewById(R.id.mercoledì).setVisibility(View.GONE);findViewById(R.id.giovedì).setVisibility(View.GONE);findViewById(R.id.venerdì).setVisibility(View.GONE);findViewById(R.id.sabato).setVisibility(View.GONE);findViewById(R.id.domenica).setVisibility(View.GONE);findViewById(R.id.orariContactItem1).setVisibility(View.GONE);findViewById(R.id.idcaccaorari).setVisibility(View.GONE);}
                                                                        else{if(getIntent().getStringExtra("luogo").equals("Basilischi")){logo.setImageDrawable(getResources().getDrawable(R.drawable.basilischi));XMEN= new Integer[]{R.drawable.basilischi1,R.drawable.basilischi2,R.drawable.basilischi3};((TextView)findViewById(R.id.orari1)).setText(orariolun);findViewById(R.id.orari2).setVisibility(View.GONE);findViewById(R.id.martedì).setVisibility(View.GONE);findViewById(R.id.mercoledì).setVisibility(View.GONE);findViewById(R.id.giovedì).setVisibility(View.GONE);findViewById(R.id.venerdì).setVisibility(View.GONE);findViewById(R.id.sabato).setVisibility(View.GONE);findViewById(R.id.domenica).setVisibility(View.GONE);}
                                                                            else{XMEN= new Integer[]{R.drawable.flag_france, R.drawable.flag_italy, R.drawable.flag_unionjack};}}}}}}}}}}}}}}}}
            }
        }

        for(int i=0;i<XMEN.length;i++) {

            XMENArray.add(XMEN[i]);

        }



        viewPager = (ViewPager) findViewById(R.id.pager_sponsor);
        viewPager.setAdapter(new SlideAdapter_Sponsor(SponsorSingolo.this, XMENArray));

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Integer[] finalXMEN = XMEN;
        final Runnable Update = new Runnable() {
            public void run() {
                if (viewPager.getCurrentItem()+1 == finalXMEN.length) {
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
        }, 5000, 5000);

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
    public void maps_sponsor(View view){
        //placeid: https://developers.google.com/places/place-id
        Uri gmmIntentUri;
        if(getIntent().getStringExtra("luogo").equals("Sita")){
             gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query="+sede.split("\n")[0]+",+Potenza,+PZ");
        }
        else {
            if(getIntent().getStringExtra("luogo").equals("Tito")){
            gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query="+sede.split("\n")[0]+",+Tricarico,+MT");
            }else{
             gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + sede.split("\n")[0] + ",+Irsina,+MT");}
        }
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
    public void apri_sito(View view){
        String url = indirizzo;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewPager.destroyDrawingCache();
        viewPager.removeAllViews();
    }

}
