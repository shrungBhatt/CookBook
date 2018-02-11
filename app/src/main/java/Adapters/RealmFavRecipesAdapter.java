package Adapters;

import android.content.Context;

import Model.RecipeDataRealm;
import io.realm.RealmResults;

public class RealmFavRecipesAdapter extends RealmModelAdapter<RecipeDataRealm> {

    public RealmFavRecipesAdapter(Context context, RealmResults<RecipeDataRealm> realmResults,
                                  boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}