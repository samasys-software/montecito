package com.matriot.cbin.ui;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fgs on 1/27/2018.
 */

public class MontecitoApplication extends Application
{
    @Override
    public void onCreate(){
        super.onCreate();
        //overrideFont(getApplicationContext(),"SERIF","fonts/GothamBook.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/GothamBook.ttf");
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/GothamBook.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/GothamBook.ttf");
    }
    public static void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets) {

        final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Map<String, Typeface> newMap = new HashMap<String, Typeface>();
            newMap.put(defaultFontNameToOverride.toLowerCase(), customFontTypeface);
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField("sSystemFontMap");
                staticField.setAccessible(true);
                staticField.set(null, newMap);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
                defaultFontTypefaceField.setAccessible(true);
                defaultFontTypefaceField.set(null, customFontTypeface);
            } catch (Exception e) {
                Log.e(MontecitoApplication.class.getSimpleName(), "Can not set custom font " + customFontFileNameInAssets + " instead of " + defaultFontNameToOverride);
            }
        }
    }
}
