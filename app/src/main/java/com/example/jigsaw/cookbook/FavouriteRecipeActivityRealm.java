package com.example.jigsaw.cookbook;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapters.FavRecipeRealmRecyclerViewAdapter;
import Adapters.RealmFavRecipesAdapter;
import Adapters.RecipeRecyclerViewAdapter;
import Model.BaseModel;
import Model.RecipeData;
import Model.RecipeDataRealm;
import Utility.JSONParser;
import Utility.MySharedPreferences;
import io.realm.RealmResults;
import realm.RealmController;

/**
 * Created by jigsaw on 14/1/18.
 */

public class FavouriteRecipeActivityRealm extends BaseActivity {

    private static final String TAG = "FavouriteRecipeActivity";
    private RecyclerView mFavrtRecipeRecyclerView;
    private FavRecipeRealmRecyclerViewAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_recipe);

        mFavrtRecipeRecyclerView = findViewById(R.id.faourite_recipe_recycler_view);
        mFavrtRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        setUpRecyclerView();


        setRealmAdapter(RealmController.with(this).getRecipes());
    }

    private void setRealmAdapter(RealmResults<RecipeDataRealm> recipes) {
        RealmFavRecipesAdapter realmAdapter = new
                RealmFavRecipesAdapter(this,recipes,true);

        mAdapter.setRealmAdapter(realmAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void setUpRecyclerView() {
        mAdapter = new FavRecipeRealmRecyclerViewAdapter(this);
        mFavrtRecipeRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }
}
