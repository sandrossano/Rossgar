package com.example.sandro.irsina;

/**
 * Created by sandro on 27/02/18.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import static com.example.sandro.irsina.ThreeLevelListAdapter.position_;


public class SecondLevelAdapter extends BaseExpandableListAdapter {
    private Context context;


    List<String[]> data;

    String[] headers;

    int position;


    public SecondLevelAdapter(Context context, String[] headers, List<String[]> data,int position) {
        this.context = context;
        this.data = data;
        this.headers = headers;
        this.position=position;
    }

    @Override
    public Object getGroup(int groupPosition) {

        return headers[groupPosition];
    }

    @Override
    public int getGroupCount() {

        return headers.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        ImageView immagine=(ImageView) convertView.findViewById(R.id.identchild);

            if (groupPosition == 0) {
                immagine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon3));
            }
            if (groupPosition == 1) {
                immagine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pane));
            }
            if (groupPosition == 2) {
                immagine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.rosticceria));
            }

        if (position_==1) {
            if (groupPosition == 0) {
                immagine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bb));
            }
        }
        if (position_==2) {
            convertView = inflater.inflate(R.layout.list_item_tipici, null);
        }
            TextView text = (TextView) convertView.findViewById(R.id.submenu2);
            String groupText = getGroup(groupPosition).toString();
            text.setText(groupText);

            RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.layout_item);

            return convertView;

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        String[] childData;

        childData = data.get(groupPosition);


        return childData[childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_subitem, null);

            TextView textView = (TextView) convertView.findViewById(R.id.submenu3);

            String[] childArray = data.get(groupPosition);

            String text = childArray[childPosition];

            textView.setText(text);

            return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(position_!=2) {
            String[] children = data.get(groupPosition);

            return children.length;
        }
        else{return 0;}
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
