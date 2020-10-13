package com.irsina.sandro.irsina;
/**
 * Created by sandro on 25/04/18.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import static com.irsina.sandro.irsina.Fuori.numero_random_fuori;
import static com.irsina.sandro.irsina.MainActivity.banner;
import static com.irsina.sandro.irsina.MainActivity.numero_random;
import static com.irsina.sandro.irsina.MainActivity.viewPager;
import static com.irsina.sandro.irsina.MainActivity.width_device;


public class SlideAdapter_Sponsor_banner extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public SlideAdapter_Sponsor_banner(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
        myImage.setImageResource(images.get(position));
        BitmapDrawable drawable = (BitmapDrawable) myImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)width_device, (55*banner)/160, true);
        myImage.setImageBitmap(resized);

        Log.d("density",banner+"");

        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent refresh = new Intent(context, SponsorSingolo.class);
                if (numero_random == 0) {
                    if (position == 0) {
                        refresh.putExtra("nome", "La Contessa");
                        refresh.putExtra("descrizione", "Il Ristorante Pizzeria La Contessa vi da il Benvenuto!<br><br>" +
                                "Situato nella antico centro storico di Irsina, sarà lusingato di accogliervi per accompagnarvi attraverso un viaggio culinario legato alle origini del luogo. In questo luogo potrete degustare degli ottimi piatti caserecci, curati ed elaborati con prodotti artigianali ed accompagnati da ottimi vini scelti appositamente per far scoprire ai propri clienti i sapori autentici di Irsina.<br>" +
                                "<br>" +
                                "Anche lo staff del Ristorante Pizzeria La Contessa sarà lieto di guidarvi in questa nuova esperienza culinaria, aprendovi le porte dal Martedì alla Domenica, e cercando di soddifare al meglio qualsiasi tipo di richiesta, adattando e personalizzando i suoi piatti in base ai desideri dei propri clienti.");
                        refresh.putExtra("numero", "+39 0835 629790");
                        refresh.putExtra("email", "info@ristorante-pizzerialacontessa.it");
                        refresh.putExtra("indirizzo", "Piazza Garibaldi, 12\n75022 Irsina");
                        refresh.putExtra("sito", "http://www.ristorante-pizzerialacontessa.it");
                        refresh.putExtra("orariolun", "CHIUSO");
                        refresh.putExtra("orariomar", "12:00 - 00:00");
                        refresh.putExtra("orariomer", "12:00 - 00:00");
                        refresh.putExtra("orariogio", "12:00 - 00:00");
                        refresh.putExtra("orarioven", "12:00 - 00:00");
                        refresh.putExtra("orariosab", "12:00 - 00:00");
                        refresh.putExtra("orariodom", "12:00 - 00:00");
                        refresh.putExtra("luogo", "Contessa");
                    }
                    if (position == 1) {
                        refresh.putExtra("nome", "Supermercato Fratelli Lentini");
                        refresh.putExtra("descrizione", "");
                        refresh.putExtra("numero", "0835518791");
                        refresh.putExtra("email", "");
                        refresh.putExtra("indirizzo", "Via Luigi Einaudi, 5\n75022 Irsina MT");
                        refresh.putExtra("sito", "https://www.facebook.com/FratelliLentiniSrl/");
                        refresh.putExtra("orariolun", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariomar", "08 – 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariomer", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariogio", "08 - 13:30");
                        refresh.putExtra("orarioven", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariosab", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariodom", "CHIUSO");

                        refresh.putExtra("luogo", "Lentini");
                    }
                    if (position == 2) {
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
                }
                else if (numero_random == 1) {
                    if (position == 0) {
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
                    if (position == 1) {
                        refresh.putExtra("nome", "La Contessa");
                        refresh.putExtra("descrizione", "Il Ristorante Pizzeria La Contessa vi da il Benvenuto!<br><br>" +
                                "Situato nella antico centro storico di Irsina, sarà lusingato di accogliervi per accompagnarvi attraverso un viaggio culinario legato alle origini del luogo. In questo luogo potrete degustare degli ottimi piatti caserecci, curati ed elaborati con prodotti artigianali ed accompagnati da ottimi vini scelti appositamente per far scoprire ai propri clienti i sapori autentici di Irsina.<br>" +
                                "<br>" +
                                "Anche lo staff del Ristorante Pizzeria La Contessa sarà lieto di guidarvi in questa nuova esperienza culinaria, aprendovi le porte dal Martedì alla Domenica, e cercando di soddifare al meglio qualsiasi tipo di richiesta, adattando e personalizzando i suoi piatti in base ai desideri dei propri clienti.");
                        refresh.putExtra("numero", "+39 0835 629790");
                        refresh.putExtra("email", "info@ristorante-pizzerialacontessa.it");
                        refresh.putExtra("indirizzo", "Piazza Garibaldi, 12\n75022 Irsina");
                        refresh.putExtra("sito", "http://www.ristorante-pizzerialacontessa.it");
                        refresh.putExtra("orariolun", "CHIUSO");
                        refresh.putExtra("orariomar", "12:00 - 00:00");
                        refresh.putExtra("orariomer", "12:00 - 00:00");
                        refresh.putExtra("orariogio", "12:00 - 00:00");
                        refresh.putExtra("orarioven", "12:00 - 00:00");
                        refresh.putExtra("orariosab", "12:00 - 00:00");
                        refresh.putExtra("orariodom", "12:00 - 00:00");
                        refresh.putExtra("luogo", "Contessa");
                    }
                    if (position == 2) {
                        refresh.putExtra("nome", "Supermercato Fratelli Lentini");
                        refresh.putExtra("descrizione", "");
                        refresh.putExtra("numero", "0835518791");
                        refresh.putExtra("email", "");
                        refresh.putExtra("indirizzo", "Via Luigi Einaudi, 5\n75022 Irsina MT");
                        refresh.putExtra("sito", "https://www.facebook.com/FratelliLentiniSrl/");
                        refresh.putExtra("orariolun", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariomar", "08 – 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariomer", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariogio", "08 - 13:30");
                        refresh.putExtra("orarioven", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariosab", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariodom", "CHIUSO");

                        refresh.putExtra("luogo", "Lentini");
                        }
                    }
                    else if (numero_random == 2) {
                        if (position == 0) {
                            refresh.putExtra("nome", "Supermercato Fratelli Lentini");
                            refresh.putExtra("descrizione", "");
                            refresh.putExtra("numero", "0835518791");
                            refresh.putExtra("email", "");
                            refresh.putExtra("indirizzo", "Via Luigi Einaudi, 5\n75022 Irsina MT");
                            refresh.putExtra("sito", "https://www.facebook.com/FratelliLentiniSrl/");
                            refresh.putExtra("orariolun", "08 - 13:30\n16:30 – 20:30");
                            refresh.putExtra("orariomar", "08 – 13:30\n16:30 – 20:30");
                            refresh.putExtra("orariomer", "08 - 13:30\n16:30 – 20:30");
                            refresh.putExtra("orariogio", "08 - 13:30");
                            refresh.putExtra("orarioven", "08 - 13:30\n16:30 – 20:30");
                            refresh.putExtra("orariosab", "08 - 13:30\n16:30 – 20:30");
                            refresh.putExtra("orariodom", "CHIUSO");

                            refresh.putExtra("luogo", "Lentini");
                        }
                        if (position == 1) {
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
                        if (position == 2) {
                            refresh.putExtra("nome", "La Contessa");
                            refresh.putExtra("descrizione", "Il Ristorante Pizzeria La Contessa vi da il Benvenuto!<br><br>" +
                                    "Situato nella antico centro storico di Irsina, sarà lusingato di accogliervi per accompagnarvi attraverso un viaggio culinario legato alle origini del luogo. In questo luogo potrete degustare degli ottimi piatti caserecci, curati ed elaborati con prodotti artigianali ed accompagnati da ottimi vini scelti appositamente per far scoprire ai propri clienti i sapori autentici di Irsina.<br>" +
                                    "<br>" +
                                    "Anche lo staff del Ristorante Pizzeria La Contessa sarà lieto di guidarvi in questa nuova esperienza culinaria, aprendovi le porte dal Martedì alla Domenica, e cercando di soddifare al meglio qualsiasi tipo di richiesta, adattando e personalizzando i suoi piatti in base ai desideri dei propri clienti.");
                            refresh.putExtra("numero", "+39 0835 629790");
                            refresh.putExtra("email", "info@ristorante-pizzerialacontessa.it");
                            refresh.putExtra("indirizzo", "Piazza Garibaldi, 12\n75022 Irsina");
                            refresh.putExtra("sito", "http://www.ristorante-pizzerialacontessa.it");
                            refresh.putExtra("orariolun", "CHIUSO");
                            refresh.putExtra("orariomar", "12:00 - 00:00");
                            refresh.putExtra("orariomer", "12:00 - 00:00");
                            refresh.putExtra("orariogio", "12:00 - 00:00");
                            refresh.putExtra("orarioven", "12:00 - 00:00");
                            refresh.putExtra("orariosab", "12:00 - 00:00");
                            refresh.putExtra("orariodom", "12:00 - 00:00");
                            refresh.putExtra("luogo", "Contessa");
                        }
                    }
                if (numero_random_fuori == 0) {
                    if (position == 0) {
                        refresh.putExtra("nome", "La Contessa");
                        refresh.putExtra("descrizione", "Il Ristorante Pizzeria La Contessa vi da il Benvenuto!<br><br>" +
                                "Situato nella antico centro storico di Irsina, sarà lusingato di accogliervi per accompagnarvi attraverso un viaggio culinario legato alle origini del luogo. In questo luogo potrete degustare degli ottimi piatti caserecci, curati ed elaborati con prodotti artigianali ed accompagnati da ottimi vini scelti appositamente per far scoprire ai propri clienti i sapori autentici di Irsina.<br>" +
                                "<br>" +
                                "Anche lo staff del Ristorante Pizzeria La Contessa sarà lieto di guidarvi in questa nuova esperienza culinaria, aprendovi le porte dal Martedì alla Domenica, e cercando di soddifare al meglio qualsiasi tipo di richiesta, adattando e personalizzando i suoi piatti in base ai desideri dei propri clienti.");
                        refresh.putExtra("numero", "+39 0835 629790");
                        refresh.putExtra("email", "info@ristorante-pizzerialacontessa.it");
                        refresh.putExtra("indirizzo", "Piazza Garibaldi, 12\n75022 Irsina");
                        refresh.putExtra("sito", "http://www.ristorante-pizzerialacontessa.it");
                        refresh.putExtra("orariolun", "CHIUSO");
                        refresh.putExtra("orariomar", "12:00 - 00:00");
                        refresh.putExtra("orariomer", "12:00 - 00:00");
                        refresh.putExtra("orariogio", "12:00 - 00:00");
                        refresh.putExtra("orarioven", "12:00 - 00:00");
                        refresh.putExtra("orariosab", "12:00 - 00:00");
                        refresh.putExtra("orariodom", "12:00 - 00:00");
                        refresh.putExtra("luogo", "Contessa");
                    }
                    if (position == 1) {
                        refresh.putExtra("nome", "Supermercato Fratelli Lentini");
                        refresh.putExtra("descrizione", "");
                        refresh.putExtra("numero", "0835518791");
                        refresh.putExtra("email", "");
                        refresh.putExtra("indirizzo", "Via Luigi Einaudi, 5\n75022 Irsina MT");
                        refresh.putExtra("sito", "https://www.facebook.com/FratelliLentiniSrl/");
                        refresh.putExtra("orariolun", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariomar", "08 – 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariomer", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariogio", "08 - 13:30");
                        refresh.putExtra("orarioven", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariosab", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariodom", "CHIUSO");

                        refresh.putExtra("luogo", "Lentini");
                    }
                    if (position == 2) {
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
                }
                else if (numero_random_fuori == 1) {
                    if (position == 0) {
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
                    if (position == 1) {
                        refresh.putExtra("nome", "La Contessa");
                        refresh.putExtra("descrizione", "Il Ristorante Pizzeria La Contessa vi da il Benvenuto!<br><br>" +
                                "Situato nella antico centro storico di Irsina, sarà lusingato di accogliervi per accompagnarvi attraverso un viaggio culinario legato alle origini del luogo. In questo luogo potrete degustare degli ottimi piatti caserecci, curati ed elaborati con prodotti artigianali ed accompagnati da ottimi vini scelti appositamente per far scoprire ai propri clienti i sapori autentici di Irsina.<br>" +
                                "<br>" +
                                "Anche lo staff del Ristorante Pizzeria La Contessa sarà lieto di guidarvi in questa nuova esperienza culinaria, aprendovi le porte dal Martedì alla Domenica, e cercando di soddifare al meglio qualsiasi tipo di richiesta, adattando e personalizzando i suoi piatti in base ai desideri dei propri clienti.");
                        refresh.putExtra("numero", "+39 0835 629790");
                        refresh.putExtra("email", "info@ristorante-pizzerialacontessa.it");
                        refresh.putExtra("indirizzo", "Piazza Garibaldi, 12\n75022 Irsina");
                        refresh.putExtra("sito", "http://www.ristorante-pizzerialacontessa.it");
                        refresh.putExtra("orariolun", "CHIUSO");
                        refresh.putExtra("orariomar", "12:00 - 00:00");
                        refresh.putExtra("orariomer", "12:00 - 00:00");
                        refresh.putExtra("orariogio", "12:00 - 00:00");
                        refresh.putExtra("orarioven", "12:00 - 00:00");
                        refresh.putExtra("orariosab", "12:00 - 00:00");
                        refresh.putExtra("orariodom", "12:00 - 00:00");
                        refresh.putExtra("luogo", "Contessa");
                    }
                    if (position == 2) {
                        refresh.putExtra("nome", "Supermercato Fratelli Lentini");
                        refresh.putExtra("descrizione", "");
                        refresh.putExtra("numero", "0835518791");
                        refresh.putExtra("email", "");
                        refresh.putExtra("indirizzo", "Via Luigi Einaudi, 5\n75022 Irsina MT");
                        refresh.putExtra("sito", "https://www.facebook.com/FratelliLentiniSrl/");
                        refresh.putExtra("orariolun", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariomar", "08 – 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariomer", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariogio", "08 - 13:30");
                        refresh.putExtra("orarioven", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariosab", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariodom", "CHIUSO");

                        refresh.putExtra("luogo", "Lentini");
                    }
                }
                else if (numero_random_fuori == 2) {
                    if (position == 0) {
                        refresh.putExtra("nome", "Supermercato Fratelli Lentini");
                        refresh.putExtra("descrizione", "");
                        refresh.putExtra("numero", "0835518791");
                        refresh.putExtra("email", "");
                        refresh.putExtra("indirizzo", "Via Luigi Einaudi, 5\n75022 Irsina MT");
                        refresh.putExtra("sito", "https://www.facebook.com/FratelliLentiniSrl/");
                        refresh.putExtra("orariolun", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariomar", "08 – 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariomer", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariogio", "08 - 13:30");
                        refresh.putExtra("orarioven", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariosab", "08 - 13:30\n16:30 – 20:30");
                        refresh.putExtra("orariodom", "CHIUSO");

                        refresh.putExtra("luogo", "Lentini");
                    }
                    if (position == 1) {
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
                    if (position == 2) {
                        refresh.putExtra("nome", "La Contessa");
                        refresh.putExtra("descrizione", "Il Ristorante Pizzeria La Contessa vi da il Benvenuto!<br><br>" +
                                "Situato nella antico centro storico di Irsina, sarà lusingato di accogliervi per accompagnarvi attraverso un viaggio culinario legato alle origini del luogo. In questo luogo potrete degustare degli ottimi piatti caserecci, curati ed elaborati con prodotti artigianali ed accompagnati da ottimi vini scelti appositamente per far scoprire ai propri clienti i sapori autentici di Irsina.<br>" +
                                "<br>" +
                                "Anche lo staff del Ristorante Pizzeria La Contessa sarà lieto di guidarvi in questa nuova esperienza culinaria, aprendovi le porte dal Martedì alla Domenica, e cercando di soddifare al meglio qualsiasi tipo di richiesta, adattando e personalizzando i suoi piatti in base ai desideri dei propri clienti.");
                        refresh.putExtra("numero", "+39 0835 629790");
                        refresh.putExtra("email", "info@ristorante-pizzerialacontessa.it");
                        refresh.putExtra("indirizzo", "Piazza Garibaldi, 12\n75022 Irsina");
                        refresh.putExtra("sito", "http://www.ristorante-pizzerialacontessa.it");
                        refresh.putExtra("orariolun", "CHIUSO");
                        refresh.putExtra("orariomar", "12:00 - 00:00");
                        refresh.putExtra("orariomer", "12:00 - 00:00");
                        refresh.putExtra("orariogio", "12:00 - 00:00");
                        refresh.putExtra("orarioven", "12:00 - 00:00");
                        refresh.putExtra("orariosab", "12:00 - 00:00");
                        refresh.putExtra("orariodom", "12:00 - 00:00");
                        refresh.putExtra("luogo", "Contessa");
                    }
                }
                context.startActivity(refresh);
            }
        });
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
