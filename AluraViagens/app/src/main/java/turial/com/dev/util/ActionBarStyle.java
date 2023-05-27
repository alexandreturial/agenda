package turial.com.dev.util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.appcompat.app.ActionBar;

public class ActionBarStyle {
    public static void setActionBarStyle(ActionBar actionBar ){
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#7b1fa2"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setElevation(90.0F);
    }
}
