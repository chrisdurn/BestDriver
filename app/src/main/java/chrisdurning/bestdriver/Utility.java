package chrisdurning.bestdriver;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by chrisdurning on 27/06/2017.
 */

class Utility {

    static void addStringToList(Context context, String content, String key){

        String list = getStringFromPreferences(context, key);

        if(list != null) {
            list = list + "%%@@" + content;
        } else {
            list = content;
        }

        putStringInPreferences(context,list,key);
    }

    static ArrayList<String> getListOfStrings(Context context, String key){
        String list = getStringFromPreferences(context, key);
        return new ArrayList<>(Arrays.asList(convertStringToArray(list)));
    }

    public static void putStringInPreferences(Context context, String content, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, content);
        editor.apply();
    }

    public static String getStringFromPreferences(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public static void putFloatInPreferences(Context context, Float content, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, content);
        editor.apply();
    }

    public static Float getFloatFromPreferences(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,MODE_PRIVATE);
        return sharedPreferences.getFloat(key, 0.0f);
    }

    public static void putIntInPreferences(Context context, int count, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, count);
        editor.apply();
    }

    public static int getIntFromPreferences(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    private static String[] convertStringToArray(String str){
        return str.split("%%@@");
    }

    static void removeStringFromSharedPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    static boolean getBooleanFromPreferences(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    static String getCallTime(Context context) {
        String format = "HH:mm:ss";
        SimpleDateFormat sdf =new SimpleDateFormat(format, Locale.ENGLISH);
        String callstarttime = sdf.format(Calendar.getInstance().getTime());
        return callstarttime;
    }

    static void putBooleanInPreferences(Context context, Boolean value, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}
