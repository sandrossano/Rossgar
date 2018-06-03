package com.example.sandro.irsina;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.qozix.tileview.TileView;

/**
 * Created by sandro on 17/05/18.
 */

public class MapTile extends Activity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        TileView tileView = new TileView(this);
        tileView.setSize(1550, 1600);  // the original size of the untiled image
        tileView.addDetailLevel(1f, "tiles/1000_%col%_%row%.png");
        tileView.addDetailLevel(0.5f, "tiles/500_%col%_%row%.png");
        tileView.addDetailLevel(0.25f, "tiles/250_%col%_%row%.png");
        tileView.addDetailLevel(0.125f, "tiles/125_%col%_%row%.png");
        tileView.slideToAndCenter(1550, 1600);
        tileView.scrollToAndCenter(1550, 1600);
        tileView.setScale((float) 0.25);
        setContentView(tileView);
    }
}
