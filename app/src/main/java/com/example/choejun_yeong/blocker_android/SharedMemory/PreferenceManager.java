package com.example.choejun_yeong.blocker_android.SharedMemory;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceManager {

    private static String BASE_STORE_PROPERTY_NAME = "pre";
    private static String WALLET_PATH = "PATH";
    private static String WALLET_PASSWORD = "PASSWORD";

    private static SharedPreferences pref = null;

    public static void setManager(Context context) {
        if(pref == null) {
            pref = context.getSharedPreferences(
                    BASE_STORE_PROPERTY_NAME,
                    MODE_PRIVATE);
        }
    }

    public static void setWalletPath(String path){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(WALLET_PATH,path);
        editor.commit();
    }

    public static String getWalletPath() {
        return pref.getString(WALLET_PATH, "");
    }

    public static void setWalletPassword(String password){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(WALLET_PASSWORD,password);
        editor.commit();
    }

    public static String getWalletPassword(){
        return pref.getString(WALLET_PASSWORD,"");
    }

}
