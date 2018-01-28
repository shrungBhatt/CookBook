package Utility;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by jigsaw on 7/1/18.
 */

public class MySharedPreferences {

    private static String PREF_RECIPE_ARRAY_JSON = "allRecipeArrayList";
    private static String PREF_FAVRT_RECIPE_ARRAY_JSON = "favrtRecipeArrayList";
    private static String PREF_RECIPE_BYCATEGORY = "recipeByCategory";





    public static String getAllRecipeArrayJson(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_RECIPE_ARRAY_JSON,null);
    }

    public static void setAllRecipeArrayJson(Context context, String arrayJson){
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(PREF_RECIPE_ARRAY_JSON,arrayJson).apply();

    }

    public static String getFavrtRecipeArrayJson(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_FAVRT_RECIPE_ARRAY_JSON,null);
    }

    public static void setFavrtRecipeArrayJson(Context context, String arrayJson){
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(PREF_FAVRT_RECIPE_ARRAY_JSON,arrayJson).apply();

    }

    public static String getRecipeDatasByCategory(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_RECIPE_BYCATEGORY,null);
    }

    public static void setRecipeDatasByCategory(Context context, String arrayJson){
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(PREF_RECIPE_BYCATEGORY,arrayJson).apply();

    }



}
