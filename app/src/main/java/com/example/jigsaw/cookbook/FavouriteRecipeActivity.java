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

import Adapters.RecipeRecyclerViewAdapter;
import Model.BaseModel;
import Utility.JSONParser;
import Utility.MySharedPreferences;

/**
 * Created by jigsaw on 14/1/18.
 */

public class FavouriteRecipeActivity extends BaseActivity {

    private static final String TAG = "FavouriteRecipeActivity";
    private RecyclerView mFavrtRecipeRecyclerView;
    private List<RecipeData> mRecipeDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_recipe);

        mFavrtRecipeRecyclerView = findViewById(R.id.faourite_recipe_recycler_view);
        mFavrtRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        fetchFavRecipes(this, ",");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                fetchFavRecipes(FavouriteRecipeActivity.this, s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                fetchFavRecipes(FavouriteRecipeActivity.this, s);
                return true;
            }
        });
        return true;
    }

    private void fetchFavRecipes(final Context context, final String query) {

        showProgressBar(context,TAG);
        //Building the request to be send to the server.
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/fetch_favourite_recipe.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        hideProgressBar();
                        try {
                            MySharedPreferences.setFavrtRecipeArrayJson(context, response);
                            //Parsing the JSON string that we received in the response of the request.
                            mRecipeDatas = JSONParser.getRecipeDatas(response);

                            //Setting the recyclerView with the ArrayList that we obtained by parsing
                            //the response string.
                            if (mRecipeDatas.size() != 0) {
                                mFavrtRecipeRecyclerView.
                                        setAdapter(new RecipeRecyclerViewAdapter(context, mRecipeDatas, 1));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideProgressBar();
                        Log.e(TAG, "Got an error: " + error.toString());
                    }
                }) {

            //This interface is called to set the value of parameters in the server-side script
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if (query != null) {
                    params.put("ingredient", query);
                }
                return params;
            }
        };

        //Add the Request that we generated in a queue which executes the requests.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }
}
