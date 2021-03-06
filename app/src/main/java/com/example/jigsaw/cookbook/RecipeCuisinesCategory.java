package com.example.jigsaw.cookbook;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
import Model.RecipeData;
import Utility.JSONParser;
import Utility.MySharedPreferences;
import Utility.SimpleDividerItemDecoration;

/**
 * Created by jigsaw on 17/1/18.
 */

public class RecipeCuisinesCategory extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "RecipeCuisinesActivity";

    private RecyclerView mRecyclerView;
    private List<RecipeData> mRecipeDatas;
    private Spinner mCuisinesSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_cuisines);

        mCuisinesSpinner = findViewById(R.id.cuisines_spinner);

        mRecyclerView = findViewById(R.id.recipe_cuisines_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        init();

    }


    private void init() {

        setAdapter(R.array.type_of_cuisines, mCuisinesSpinner);

    }


    private void setAdapter(int id, Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                id, R.layout.simple_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void fetchRecipeAccToCuisines(final Context context, final String cuisines) {
        showProgressBar(context,TAG);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/fetch_recipe_by_cuisines.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        hideProgressBar();
                        try {
                            mRecipeDatas = JSONParser.getRecipeDatas(response);

                            if (mRecipeDatas.size() != 0) {

                                MySharedPreferences.setRecipeDatasByCuisines(context,response);

                                mRecyclerView.setAdapter(new RecipeRecyclerViewAdapter(context,mRecipeDatas,3));

                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideProgressBar();
                        Log.e(TAG,error.toString());
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if (cuisines != null) {
                    params.put("cuisines", cuisines);
                }
                return params;
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }


    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String cuisines = adapterView.getItemAtPosition(i).toString().trim();

        fetchRecipeAccToCuisines(this,cuisines);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
