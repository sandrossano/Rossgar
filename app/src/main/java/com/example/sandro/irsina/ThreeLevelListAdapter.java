package com.example.sandro.irsina;

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
            immagine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.hotel));
        }
        if(groupPosition==2) {
            immagine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.shop));
        }
        TextView text = (TextView) convertView.findViewById(R.id.submenu1);
        text.setText(this.parentHeaders.get(groupPosition));

        return convertView;

    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, final View convertView, ViewGroup parent) {

            final SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);

            String[] headers = secondLevel.get(groupPosition);


            List<String[]> childData = new ArrayList<>();
            HashMap<String, String[]> secondLevelData=null;
            if(position_!=2) {
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

                    if (position_==2){
                        Intent visualizza = new Intent(context, Cancelle.class);
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
                    Intent visualizza = new Intent(context, SponsorSingolo.class);
                    context.startActivity(visualizza);
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
