package ua.gram.munhauzen;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by Pujan KC on 11/27/2019.
 */
public class SharedPreferencesHelper {

    private static final String KEY_TIME = "key_time";

    private  static SharedPreferences sharedPreferences;

    public static void setTime(Context context, String token){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(KEY_TIME, token).apply();
    }

    public static String getTime(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(KEY_TIME, "");
    }


}
