package com.example.sandro.irsina;

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

import static com.example.sandro.irsina.MainActivity.banner;
import static com.example.sandro.irsina.MainActivity.viewPager;
import static com.example.sandro.irsina.MainActivity.width_device;


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
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
        myImage.setImageResource(images.get(position));
        BitmapDrawable drawable = (BitmapDrawable) myImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        int density=50;
        if(banner==160){density=38;}
        if(banner==240){density=50;}
        if(banner==320){density=75;}
        if(banner==480){density=100;}
        if(banner==640){density=150;}
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)width_device, (50*banner)/160, true);
        myImage.setImageBitmap(resized);

        Log.d("density",banner+"");

        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent refresh = new Intent(context, SponsorSingolo.class);
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
