package com.mash.pig.bobpercent.app.widget;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.mash.pig.bobpercent.BobPercentApplication;

public class FontHelper {

    private volatile static FontHelper instance;

    public static FontHelper getInstance() {
        if (instance == null) {
            synchronized (FontHelper.class) {
                if (instance == null) {
                    instance = new FontHelper();
                }
            }
        }

        return instance;
    }

    private Typeface fontRegular;

    public FontHelper() {
        AssetManager assets = BobPercentApplication.getInstance().getAssets();
        fontRegular = Typeface.createFromAsset(assets, "Stanberry.ttf");
    }

    public synchronized Typeface getRegular() {
        return fontRegular;
    }
}