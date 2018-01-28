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

public class AllRecipeActivity extends BaseActivity {

    private static final String TAG = "AllRecipeActivity";


    private List<RecipeData> mRecipeDatas;
    private RecyclerView mRecipeRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        mRecipeRecyclerView = findViewById(R.id.activity_recipe_recycler_view);

        mRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchRecipes(this,",");


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
                fetchRecipes(AllRecipeActivity.this,s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                fetchRecipes(AllRecipeActivity.this,s);
                return true;
            }
        });
        return true;
    }

    private void fetchRecipes(final Context context, final String query) {

        //Building the request to be send to the server.
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/recipe.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            MySharedPreferences.setAllRecipeArrayJson(context, response);
                            //Parsing the JSON string that we received in the response of the request.
                            mRecipeDatas = JSONParser.getRecipeDatas(response);
                            RecipeData.setmRecipeDatas(mRecipeDatas);

                            //Setting the recyclerView with the ArrayList that we obtained by parsing
                            //the response string.
                            if (mRecipeDatas.size() != 0) {
                                mRecipeRecyclerView.
                                        setAdapter(new RecipeRecyclerViewAdapter(context, mRecipeDatas, 0));
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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

    //Method used to parse the JSON that we got in the response from the server.


    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }

}
