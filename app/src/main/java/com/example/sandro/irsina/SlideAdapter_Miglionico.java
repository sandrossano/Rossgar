package com.example.sandro.irsina;

/**
 * Created by sandro on 25/04/18.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class SlideAdapter_Miglionico extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;
    private String [] titoli={"Visitazione","Vergine tra San Giovanni Battista","Adorazione del Bambino Gesù"};
    public SlideAdapter_Miglionico(Context context, ArrayList<Integer> images) {
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
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.slidetext, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
        myImage.setImageResource(images.get(position));
        TextView text=(TextView)myImageLayout.findViewById(R.id.textView10) ;
        text.setVisibility(View.VISIBLE);

        text.setText(titoli[position]);
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
