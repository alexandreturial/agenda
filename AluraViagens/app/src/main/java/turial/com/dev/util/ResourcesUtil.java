package turial.com.dev.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;


public class ResourcesUtil {
    public static Drawable devolverDrawable(String drawableText, Context context) {
        Resources resources = context.getResources();
        int idDrawable = resources.getIdentifier(drawableText, "drawable", context.getPackageName());
        return resources.getDrawable(idDrawable, context.getTheme());
    }
}
