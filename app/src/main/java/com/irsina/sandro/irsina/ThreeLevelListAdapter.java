package com.irsina.sandro.irsina;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ThreeLevelListAdapter extends BaseExpandableListAdapter{

    ArrayList<String> parentHeaders;
    List<String[]> secondLevel;
    private Context context;
    List<LinkedHashMap<String, String[]>> data;
    static int position_=0;

    /**
     * Constructor
     * @param context
     * @param parentHeader
     * @param secondLevel
     * @param data
     */
    public ThreeLevelListAdapter(Context context, ArrayList<String> parentHeader, List<String[]> secondLevel, List<LinkedHashMap<String, String[]>> data) {
        this.context = context;

        this.parentHeaders = parentHeader;

        this.secondLevel = secondLevel;

        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return parentHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return 1;

    }

    @Override
    public Object getGroup(int groupPosition) {

        return groupPosition;
    }

    @Override
    public Object getChild(int group, int child) {


        return child;


    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        position_=groupPosition;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_group, null);
        ImageView immagine=(ImageView) convertView.findViewById(R.id.ident);
        if(groupPosition==0) {
            immagine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.food));
        }
        if(groupPosition==1) {
            immagine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.shop));
        }
        if(groupPosition==2) {
            immagine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_shopping_cart_black_24dp));
        }
        if(groupPosition==3) {
            immagine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.hotel));
        }
        if(groupPosition==4) {
            immagine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.road));
        }
        TextView text = (TextView) convertView.findViewById(R.id.submenu1);
        text.setText(this.parentHeaders.get(groupPosition));

        return convertView;

    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, final View convertView, ViewGroup parent) {

            final SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);

            String[] headers = secondLevel.get(groupPosition);


            List<String[]> childData = new ArrayList<>();
            HashMap<String, String[]> secondLevelData=null;
            if(position_!=1) {
                secondLevelData = data.get(groupPosition);

                for (String key : secondLevelData.keySet()) {


                    childData.add(secondLevelData.get(key));

                }
            }

            secondLevelELV.setAdapter(new SecondLevelAdapter(context, headers, childData, groupPosition));

            secondLevelELV.setGroupIndicator(null);
            secondLevelELV.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                    position_ = groupPosition;
                    Log.d("secondolivello", "Hai premuto: ");

                    if (position_==1){
                        Intent visualizza = new Intent(context, Cancelle.class);
                        if(i==0){visualizza.putExtra("premuto","Degustazione");}
                        if(i==1){visualizza.putExtra("premuto","Carne Podolica");}
                        if(i==2){visualizza.putExtra("premuto","Cancelle");}
                        if(i==3){visualizza.putExtra("premuto","Mastacciole");}

                        context.startActivity(visualizza);

                    }

                    return false;

                }
            });

            secondLevelELV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                    Log.d("primo", "Hai premuto: " + groupPosition);

                    Log.d("secondolivello", "Hai premuto: " + i);

                    Log.d("terzolivello", "Hai premuto: " + i1);
                    Intent refresh = new Intent(context, SponsorSingolo.class);
                    if(i1==1 && groupPosition==0 & i==0) {
                        refresh.putExtra("nome", "Trattoria Nugent");
                        refresh.putExtra("descrizione", "<b>Alla riscoperta degli antichi sapori della cucina tipica e tradizionale</b><br><br>Il locale è all’interno del Cortile dello storico Palazzo Ducale Nugent, situato nel cuore del<br>" +
                                "suggestivo centro storico di Irsina a due passi dalla Cattedrale.<br>" +
                                "L'ambiente è rustico, volte alte, mattoni a vista e alle pareti ci sono attrezzi utilizzati un tempo per  <br>" +
                                "lavorare la terra che rendono questa trattoria quasi un piccolo museo della civiltà contadina. <br>" +
                                "L'ambiente è coerente con il cibo che viene servito. Proponiamo infatti piatti della tradizione  <br>" +
                                "Irsinese. <br>" +
                                "Lavoriamo per la promozione dei prodotti tipici e la riscoperta della cucina tradizionale, troppo  <br>" +
                                "spesso lasciata solo ai ricordi o alle mani esperte di qualche nonna.  <br>" +
                                "Per ottenere il risultato migliore la selezione delle materie prime è fatta sul territorio, per una  <br>" +
                                "maggiore garanzia di qualità, per valorizzare i produttori locali, e non ultimo, per ridurre l’impatto  <br>" +
                                "ambientale, secondo le buone norme del consumo di prodotti a km 0.");
                        refresh.putExtra("numero", "+39 0835 628180");
                        refresh.putExtra("email", "");
                        refresh.putExtra("indirizzo", "Piazza Garibaldi, 6\n75022 Irsina");
                        refresh.putExtra("sito", "https://www.facebook.com/TRATTORIA-NUGENT-DA-MARIO-E-GIGIA-40393968439");
                        refresh.putExtra("orariolun", "12:00 - 15:00\n" +
                                "19:00 - 00:00");
                        refresh.putExtra("orariomar", "CHIUSO");
                        refresh.putExtra("orariomer", "12:00 - 15:00\n" +
                                "19:00 - 00:00");
                        refresh.putExtra("orariogio", "12:00 - 15:00\n" +
                                "19:00 - 00:00");
                        refresh.putExtra("orarioven", "12:00 - 15:00\n" +
                                "19:00 - 00:00");
                        refresh.putExtra("orariosab", "12:00 - 15:00\n" +
                                "19:00 - 00:00");
                        refresh.putExtra("orariodom", "12:00 - 15:00\n" +
                                "19:00 - 00:00");
                        refresh.putExtra("luogo", "Nugent");
                    }
                    else {
                        if (i1 == 0 && groupPosition == 2 & i == 1) {
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
                        } else {if (i1 == 0 && groupPosition == 0 & i == 0) {
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
                        } else {if (i1 == 0 && groupPosition == 4 & i == 0) {
                            refresh.putExtra("nome", "Sita Sud");
                            refresh.putExtra("descrizione", "Sita Sud - Sicurezza e Trasporti Autolinee s.r.l.(o più semplicemente solo Sita Sud) è una società che si occupa del trasporto passeggeri su gomma nel Sud Italia.<br>" +
                                    "<br>Basilicata<br>" +
                                    "La divisione fa parte del consorzio Cotrab e effettua linee nelle provincie di Matera e Potenza e un collegamento con l'aeroporto di Bari-Palese<br>" +
                                    "<br>" +
                                    "Puglia<br>" +
                                    "La divisione pugliese fa parte del consorzio Cotrap e effettua linee nelle provincie di Bari, Foggia, Lecce e Taranto.<br>" +
                                    "<br>" +
                                    "Campania<br>" +
                                    "Sita Sud effettua collegamenti giornalieri da Napoli e Salerno per la Penisola Sorrentina, per la Costiera Amalfitana, per i Monti Lattari per il Litorale Cilentano, per il Vallo di Diano e la Provincia di Potenza, nonché tratte con capolinea ad Avellino. Nei primi mesi del 2014 una lunga trattativa tra enti regionali e la compagnia, resa necessaria dalla minaccia di cessare ogni attività nella regione per insufficienza dei corrispettivi di esercizio nonché di uscita da UnicoCampania, si è risolta positivamente con un accordo fra Sita Sud, Regione Campania e le province di Salerno, Napoli ed Avellino.<br>" +
                                    "<br>" +
                                    "Linee Nazionali<br>" +
                                    "Sita Sud inoltre effettua anche collegamenti Salerno - Napoli - Bari.");
                            refresh.putExtra("numero", "+39 0971 506811");
                            refresh.putExtra("email", "info@sitasudtrasporti.it");
                            refresh.putExtra("indirizzo", "Via Appia, 185\n85100 Potenza");
                            refresh.putExtra("sito", "http://www.sitasudtrasporti.it/");
                            refresh.putExtra("orariolun", "\nhttp://www.sitasudtrasporti.it/orari");
                            refresh.putExtra("orariomar", "");
                            refresh.putExtra("orariomer", "");
                            refresh.putExtra("orariogio", "");
                            refresh.putExtra("orarioven", "");
                            refresh.putExtra("orariosab", "");
                            refresh.putExtra("orariodom", "");

                            refresh.putExtra("luogo", "Sita");
                        } else {if (i1 == 0 && groupPosition == 2 & i == 0) {
                            refresh.putExtra("nome", "Supermercato Fratelli Lentini");
                            refresh.putExtra("descrizione", "Il Supermercato Flli. Lentini é da sempre sinonino di qualità e passione.\n" +
                                    "Il supermercato vanta una vasta scelta di prodotti ma senza intaccare sulla qualità, solo materia di prima scelta.\n" +
                                    "All'interno potrete trovare il banco carni,con prodotti sempre freschi e di altissima qualità.\n" +
                                    "il banco salumeria, una chicca per chi ama i salumi,latticini e formaggi.\n" +
                                    "Per i clienti sarà sempre possibile acquistare dell'ottimo pane fresco.\n" +
                                    "Inoltre è possibile anche acquistare miscele caffè di produzione propria.");
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
                        } else {if (i1 == 2 && groupPosition == 0 & i == 0) {
                            refresh.putExtra("nome", "La Tavernetta-Rodari Park");
                            refresh.putExtra("descrizione", "<b>Benvenuto!</b><br><br>" +
                                    "HAI VOGLIA DI GUSTARE QUALCOSA DI  DIVERSO?<br>SEI ARRIVATO NEL POSTO GIUSTO, DA NOI TROVI UNA BUONA CUCINA MEDITTERANEA,PIZZA COTTA CON FORNO A LEGNA, TUTTI I CIBI POSSONO ESSERE ACCOMPAGNATI DA VINI LOCALI E BIRRE  ITALIANE ED ESTERE.\nSIAMO SITI IN IRSINA -MT- SARAI  IL BENVENUTO...");
                            refresh.putExtra("numero", "0835 518846");
                            refresh.putExtra("email", "raffaelelasaponara@gmail.com");
                            refresh.putExtra("indirizzo", "Piazza Marx, 3\n75022 Irsina MT");
                            refresh.putExtra("sito", "https://vincenzodp19.wixsite.com/latavernetta");
                            refresh.putExtra("orariolun", "11–00:15");
                            refresh.putExtra("orariomar", "CHIUSO");
                            refresh.putExtra("orariomer", "11–00:15");
                            refresh.putExtra("orariogio", "11–00:15");
                            refresh.putExtra("orarioven", "11–00:15");
                            refresh.putExtra("orariosab", "11–00:15");
                            refresh.putExtra("orariodom", "11–00:15");

                            refresh.putExtra("luogo", "Rodary");
                        } else {if (i1 == 3 && groupPosition == 0 & i == 0) {
                            refresh.putExtra("nome", "Ducale");
                            refresh.putExtra("descrizione", "<b>Caffè, pizzeria e ristorante tipico, a Irsina</b><br><br>" +
                                    "Il Ducale è caffè, ristorante e pizzeria a Irsina, in provincia di Matera. Situato sui resti della vecchia torre normanna del feudo di Montepeloso, in Piazza Garibaldi 1, è il luogo ideale per una semplice pausa caffè o anche per una sosta a pranzo o a cena dopo una visita al centro storico di Irsina.<br>Il Ducale unisce al locale confortevole e innovativo una ricca offerta di piatti della tradizione contadina locale, regalandovi un viaggio nei sapori della terra lucana.<br>Per la pizza, utilizza solo ingredienti di prima qualità, lievito madre e farine nobili: di farro, di grano duro Senatore Cappelli, di grano arso e ai cinque cereali...per una gustosa digeribilità. A vostra disposizione, il servizio Wi-Fi gratuito e un'ampia terrazza panoramica.");
                            refresh.putExtra("numero", "0835 628190");
                            refresh.putExtra("email", "giovannigiorgio@live.it");
                            refresh.putExtra("indirizzo", "Piazza Garibaldi, 1\n75022 Irsina MT");
                            refresh.putExtra("sito", "https://www.ilducalepizzeriaristorante-irsina.it/");
                            refresh.putExtra("orariolun", "CHIUSO");
                            refresh.putExtra("orariomar", "7:30 - 2:00");
                            refresh.putExtra("orariomer", "7:30 - 2:00");
                            refresh.putExtra("orariogio", "7:30 - 2:00");
                            refresh.putExtra("orarioven", "7:30 - 2:00");
                            refresh.putExtra("orariosab", "7:30 - 2:00");
                            refresh.putExtra("orariodom", "7:30 - 2:00");

                            refresh.putExtra("luogo", "Ducale");
                        } else {if (i1 == 4 && groupPosition == 0 & i == 0) {
                            refresh.putExtra("nome", "Fuoco di Vino");
                            refresh.putExtra("descrizione", "");
                            refresh.putExtra("numero", "0835 518889");
                            refresh.putExtra("email", "");
                            refresh.putExtra("indirizzo", "Corso G. Matteotti, 7\n75022 Irsina MT");
                            refresh.putExtra("sito", "http://www.fuocodivinoirsina.it");
                            refresh.putExtra("orariolun", "18:30 – 01");
                            refresh.putExtra("orariomar", "18:30 – 01");
                            refresh.putExtra("orariomer", "CHIUSO");
                            refresh.putExtra("orariogio", "18:30 – 01");
                            refresh.putExtra("orarioven", "18:30 – 01");
                            refresh.putExtra("orariosab", "18:30 – 01");
                            refresh.putExtra("orariodom", "18:30 – 01");

                            refresh.putExtra("luogo", "Fuoco");
                        } else {if (i1 == 0 && groupPosition == 4 & i == 1) {
                            refresh.putExtra("nome", "Car City Service");
                            refresh.putExtra("descrizione", "");
                            refresh.putExtra("numero", "0835 628029");
                            refresh.putExtra("email", "");
                            refresh.putExtra("indirizzo", "C.so di Vittorio, 21\n75022 Irsina");
                            refresh.putExtra("sito", "https://www.facebook.com/Car-City-Service-965139166908402/");
                            refresh.putExtra("orariolun", "");
                            refresh.putExtra("orariomar", "");
                            refresh.putExtra("orariomer", "");
                            refresh.putExtra("orariogio", "");
                            refresh.putExtra("orarioven", "");
                            refresh.putExtra("orariosab", "");
                            refresh.putExtra("orariodom", "");

                            refresh.putExtra("luogo", "Car");
                        } else {if (i1 == 1 && groupPosition == 0 & i == 2) {
                            refresh.putExtra("nome", "Rosticceria Despar");
                            refresh.putExtra("descrizione", "");
                            refresh.putExtra("numero", "0835 628029");
                            refresh.putExtra("email", "");
                            refresh.putExtra("indirizzo", "C.so di Vittorio, 21\n75022 Irsina");
                            refresh.putExtra("sito", "https://www.facebook.com/Car-City-Service-965139166908402/");
                            refresh.putExtra("orariolun", "CHIUSO");
                            refresh.putExtra("orariomar", "CHIUSO");
                            refresh.putExtra("orariomer", "CHIUSO");
                            refresh.putExtra("orariogio", "CHIUSO");
                            refresh.putExtra("orarioven", "CHIUSO");
                            refresh.putExtra("orariosab", "CHIUSO");
                            refresh.putExtra("orariodom", "CHIUSO");

                            refresh.putExtra("luogo", "Rosticceria Despar");
                        } else {if (i1 == 1 && groupPosition == 2 & i == 0) {
                            refresh.putExtra("nome", "Despar");
                            refresh.putExtra("descrizione", "");
                            refresh.putExtra("numero", "0835 628029");
                            refresh.putExtra("email", "");
                            refresh.putExtra("indirizzo", "C.so di Vittorio, 21\n75022 Irsina");
                            refresh.putExtra("sito", "https://www.facebook.com/Car-City-Service-965139166908402/");
                            refresh.putExtra("orariolun", "");
                            refresh.putExtra("orariomar", "");
                            refresh.putExtra("orariomer", "");
                            refresh.putExtra("orariogio", "");
                            refresh.putExtra("orarioven", "");
                            refresh.putExtra("orariosab", "");
                            refresh.putExtra("orariodom", "");

                            refresh.putExtra("luogo", "Despar");
                        } else {if (i1 == 2 && groupPosition == 2 & i == 0) {
                            refresh.putExtra("nome", "Crai");
                            refresh.putExtra("descrizione", "");
                            refresh.putExtra("numero", "0835 518948");
                            refresh.putExtra("email", "");
                            refresh.putExtra("indirizzo", "C.so di Vittorio, 21\n75022 Irsina");
                            refresh.putExtra("sito", "");
                            refresh.putExtra("orariolun", "08:00 - 13:00\n16:30 - 20:30");
                            refresh.putExtra("orariomar", "08:00 - 13:00\n16:30 - 20:30");
                            refresh.putExtra("orariomer", "08:00 - 13:00\n16:30 - 20:30");
                            refresh.putExtra("orariogio", "08:00 - 13:00\n16:30 - 20:30");
                            refresh.putExtra("orarioven", "08:00 - 13:00\n16:30 - 20:30");
                            refresh.putExtra("orariosab", "08:00 - 13:00\n16:30 - 20:30");
                            refresh.putExtra("orariodom", "CHIUSO");

                            refresh.putExtra("luogo", "Crai");
                        } else {if (i1 == 0 && groupPosition == 0 & i == 1) {
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
                        } else {if (i1 == 0 && groupPosition == 0 & i == 2) {
                            refresh.putExtra("nome", "Le Spighe Cafè");
                            refresh.putExtra("descrizione", "");
                            refresh.putExtra("numero", "0835 518337");
                            refresh.putExtra("email", "");
                            refresh.putExtra("indirizzo", "Corso Musacchio, 8\n75022 Irsina MT");
                            refresh.putExtra("sito", "https://www.facebook.com/Le-Spighe-1658651584394807/");
                            refresh.putExtra("orariolun", "06:00 – 13:30\n14:30 – 00:00");
                            refresh.putExtra("orariomar", "06:00 – 13:30\n14:30 – 00:00");
                            refresh.putExtra("orariomer", "CHIUSO");
                            refresh.putExtra("orariogio", "06:00 – 13:30\n14:30 – 00:00");
                            refresh.putExtra("orarioven", "06:00 – 13:30\n14:30 – 00:00");
                            refresh.putExtra("orariosab", "06:00 – 13:30\n14:30 – 00:00");
                            refresh.putExtra("orariodom", "06:00 – 13:30\n14:30 – 00:00");

                            refresh.putExtra("luogo", "Spighe");
                        } else {if (i1 == 1 && groupPosition == 4 & i == 0) {
                            refresh.putExtra("nome", "Tito Bus");
                            refresh.putExtra("descrizione", "La Titobus srl e’ un azienda che opera nel settore dei trasporti pubblici locali , e del noleggio con conducente di autobus ed autovetture.<br>" +
                                    "Possiede autovetture ed autobus di proprietà e di recentissima immatricolazione, con capienza che va dai 4(quattro) ai 60(sessanta) posti, per poter soddisfare ogni esigenza della propria clientela e dei tour operator nazionali ed internazionali.<br>" +
                                    "Nell’ottica di un costante miglioramento del servizio ,nel corso degli anni, l’azienda ha attuato una politica di continuo rinnovamento dei sistemi operativi , gestionali e del parco automezzi, per offrire sempre maggiore sicurezza ,affidabilita’ ed efficienza.");
                            refresh.putExtra("numero", "0835 726017");
                            refresh.putExtra("email", "info@titobus.it");
                            refresh.putExtra("indirizzo", "Via Appia snc\n75019 Tricarico MT");
                            refresh.putExtra("sito", "http://www.titobus.it");
                            refresh.putExtra("orariolun", "\nhttp://www.titobus.it/autolinee/");
                            refresh.putExtra("orariomar", "");
                            refresh.putExtra("orariomer", "");
                            refresh.putExtra("orariogio", "");
                            refresh.putExtra("orarioven", "");
                            refresh.putExtra("orariosab", "");
                            refresh.putExtra("orariodom", "");

                            refresh.putExtra("luogo", "Tito");
                        } else {if (i1 == 2 && groupPosition == 4 & i == 0) {
                            refresh.putExtra("nome", "Smaldone Bus");
                            refresh.putExtra("descrizione", "<b>Dati non Disponibili</b>");
                            refresh.putExtra("numero", "");
                            refresh.putExtra("email", "");
                            refresh.putExtra("indirizzo", "");
                            refresh.putExtra("sito", "");
                            refresh.putExtra("orariolun", "");
                            refresh.putExtra("orariomar", "");
                            refresh.putExtra("orariomer", "");
                            refresh.putExtra("orariogio", "");
                            refresh.putExtra("orarioven", "");
                            refresh.putExtra("orariosab", "");
                            refresh.putExtra("orariodom", "");

                            refresh.putExtra("luogo", "Smaldone");
                            } else {if (i1 == 3 && groupPosition == 2 & i == 0) {
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
                        } else {if (i1 == 0 && groupPosition == 3 & i == 0) {
                            refresh.putExtra("nome", "I Basilischi");
                            refresh.putExtra("descrizione", "Il b&b Basilischi Mette a disposizione dei propri ospiti una struttura ampia e accogliente, ideale per tutti coloro che sono alla ricerca di un luogo rilassante in cui trascorrere soggiorni indimenticabili in un Comune ricco di storia.");
                            refresh.putExtra("numero", "+39 3661067341");
                            refresh.putExtra("email", "ibasilischi@ibasilischi.it");
                            refresh.putExtra("indirizzo", "Piazza Costa 8A\n75022 Irsina");
                            refresh.putExtra("sito", "http://www.ibasilischi.it");
                            refresh.putExtra("orariolun", "\nTutti i Giorni: 00:00 - 24:00");
                            refresh.putExtra("orariomar", "");
                            refresh.putExtra("orariomer", "");
                            refresh.putExtra("orariogio", "");
                            refresh.putExtra("orarioven", "");
                            refresh.putExtra("orariosab", "");
                            refresh.putExtra("orariodom", "");
                            refresh.putExtra("luogo", "Basilischi");
                        } else {
                            refresh.putExtra("luogo", "");
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    context.startActivity(refresh);
                    return false;

                }
            });

            secondLevelELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                int previousGroup = -1;

                @Override
                public void onGroupExpand(int groupPosition) {
                    if (groupPosition != previousGroup)
                        secondLevelELV.collapseGroup(previousGroup);
                    previousGroup = groupPosition;
                }
            });


            return secondLevelELV;

    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
