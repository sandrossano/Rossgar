package com.irsina.sandro.irsina;
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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

import static com.irsina.sandro.irsina.Lingua.logValue;


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
    String premuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logValue();

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

        premuto=getIntent().getStringExtra("premuto");
        setTitle(premuto);

        if (premuto.equals("Degustazione")){
            ((ImageView)findViewById(R.id.logo_dipartimento)).setImageDrawable(getResources().getDrawable(R.drawable.bottega));
            ((TextView)findViewById(R.id.nome_lista)).setText("La Bottega del Gusto");
            ((ImageView)findViewById(R.id.logo_dipartimento2)).setImageDrawable(getResources().getDrawable(R.drawable.mulino));
            ((TextView)findViewById(R.id.nome_lista2)).setText("Vecchio Mulino");
        }
        if (premuto.equals("Carne Podolica")){
            ((ImageView)findViewById(R.id.logo_dipartimento)).setImageDrawable(getResources().getDrawable(R.drawable.macelleria));
            ((TextView)findViewById(R.id.nome_lista)).setText("Macelleria Sanseverino");
            ((CardView)findViewById(R.id.card_view_esempio)).setVisibility(View.GONE);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


        ImageView imm=(ImageView) findViewById(R.id.immagineSingolo);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer.removeView(navigationView);


        TextView wow1= (TextView) findViewById(R.id.tvNumber1);
        wow1.setText(telefono_fisso);
        TextView wow2= (TextView) findViewById(R.id.tvNumber3);
        wow2.setText(email);
        TextView wow5= (TextView) findViewById(R.id.tvNumber5);
        wow5.setText(sede);
        TextView wow4= (TextView) findViewById(R.id.tvNumber4);
        wow4.setText(indirizzo);

        final Integer[] XMEN = {R.drawable.tipici};
        final Integer[] XMEN2 = {R.drawable.macelleria_slide12};

        for(int i=0;i<XMEN.length;i++) {

            if(premuto.equals("Degustazione"))XMENArray.add(XMEN[i]);
            if(premuto.equals("Carne Podolica"))XMENArray.add(XMEN2[i]);

        }

        viewPager = (ViewPager) findViewById(R.id.pager_tipici);
        viewPager.setAdapter(new SlideAdapter_Sponsor(Cancelle.this, XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator_tipici);
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
        }, 500, 4000);
    }

    public void esempio(View view) {
        Intent refresh = new Intent(getApplicationContext(), SponsorSingolo.class);
        if(premuto.equals("Degustazione")){
        refresh.putExtra("nome", "La Bottega del Gusto");
        refresh.putExtra("descrizione", "Salumeria, alimentari, taglieri salumi e formaggi, prodotti tipici lucani e vini doc");
        refresh.putExtra("numero", "+39 3312241047");
        refresh.putExtra("email", "labottegadelgustoirsina@gmail.com");
        refresh.putExtra("indirizzo", "Piazza Garibaldi, 15\n75022 Irsina");
        refresh.putExtra("sito", "https://www.goviashop.com/store/labottegadelgusto");
        refresh.putExtra("orariolun", "08:00 – 22:00");
        refresh.putExtra("orariomar", "08:00 – 22:00");
        refresh.putExtra("orariomer", "08:00 – 22:00");
        refresh.putExtra("orariogio", "08:00 – 22:00");
        refresh.putExtra("orarioven", "08:00 – 22:00");
        refresh.putExtra("orariosab", "08:00 – 22:00");
        refresh.putExtra("orariodom", "08:00 – 22:00");
        refresh.putExtra("luogo", "Bottega");
        }
        if(premuto.equals("Carne Podolica")){
            refresh.putExtra("nome", "Macelleria Sanseverino");
            refresh.putExtra("descrizione", "Da anni la Famiglia Sanseverino passa di generazione in generazione l'arte della Macelleria.<br>" +
                    "Allevamenti, pascoli e genuità contraddistingono le loro carni.<br>" +
                    "A disposizione dei clienti sempre carne genuina e di altissima qualità, non mancano mai agnelli,polli,suini,tacchini  ma la regina é sicuramente la carne podolica, dal gusto intenso, saporito e leggermente dolciastro, grazie all’alimentazione naturale, pascolo e foraggi freschi, dei bovini allevati allo stato brado. Una carne che si inserisce in una fascia di mercato alto ma la sua genuinità la collocano tra gli alimenti dall’elevato valore salutistico.<br>" +
                    "La famiglia da sempre si occupa anche della produzione del famoso \"Caciocavallo Podolico\" una vera chicca per il palato.");
            refresh.putExtra("numero", "+39 3286847200");
            refresh.putExtra("email", "");
            refresh.putExtra("indirizzo", "C.so di Vittorio 11\n75022 Irsina");
            refresh.putExtra("sito", "https://www.facebook.com/macelleriasanseverinoantonio/");
            refresh.putExtra("orariolun", "8:00 - 13:00");
            refresh.putExtra("orariomar", "8:00 - 13:00\n16:30 - 20:30");
            refresh.putExtra("orariomer", "8:00 - 13:00\n16:30 - 20:30");
            refresh.putExtra("orariogio", "8:00 - 13:00\n16:30 - 20:30");
            refresh.putExtra("orarioven", "8:00 - 13:00\n16:30 - 20:30");
            refresh.putExtra("orariosab", "8:00 - 13:00\n16:30 - 20:30");
            refresh.putExtra("orariodom", "CHIUSO");
            refresh.putExtra("luogo", "Macelleria");
        }
        startActivity(refresh);
    }
    public void esempio2(View view) {
        Intent refresh = new Intent(getApplicationContext(), SponsorSingolo.class);
        if(premuto.equals("Degustazione")){
            refresh.putExtra("nome", "Panificio Vecchio Mulino");
            refresh.putExtra("descrizione", "");
            refresh.putExtra("numero", "0835 629351");
            refresh.putExtra("email", "");
            refresh.putExtra("indirizzo", "Corso Giuseppe Mazzini, 28\n75022 Irsina MT");
            refresh.putExtra("sito", "");
            refresh.putExtra("orariolun", "");
            refresh.putExtra("orariomar", "");
            refresh.putExtra("orariomer", "");
            refresh.putExtra("orariogio", "");
            refresh.putExtra("orarioven", "");
            refresh.putExtra("orariosab", "");
            refresh.putExtra("orariodom", "");

            refresh.putExtra("luogo", "Vecchio Mulino");
        }

        startActivity(refresh);
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
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewPager.destroyDrawingCache();
        viewPager.removeAllViews();
    }}
