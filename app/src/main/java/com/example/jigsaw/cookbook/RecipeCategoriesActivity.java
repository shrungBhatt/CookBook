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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapters.RecipeCategoriesRecyclerViewAdapter;
import Adapters.RecipeRecyclerViewAdapter;
import Model.BaseModel;
import Utility.JSONParser;
import Utility.MySharedPreferences;
import Utility.SimpleDividerItemDecoration;


public class RecipeCategoriesActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "RecipeCategoriesActivity";

    private RecyclerView mRecyclerView;
    private List<RecipeData> mRecipeDatas;
    private Spinner mCategoriesSpinner;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_category);


        mCategoriesSpinner = findViewById(R.id.categories_spinner);

        mRecyclerView = findViewById(R.id.recipe_category_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));



        init();
//        populateRecyclerView();

    }


    private void init() {

        setAdapter(R.array.type_of_categories, mCategoriesSpinner);

    }


    private void setAdapter(int id, Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                id, R.layout.simple_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    private void fetchRecipeAccToCategories(final Context context, final String category) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/fetch_recipe_by_category.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            mRecipeDatas = JSONParser.getRecipeDatas(response);

                            if (mRecipeDatas.size() != 0) {

                                MySharedPreferences.setRecipeDatasByCategory(context,response);

                                mRecyclerView.setAdapter(new RecipeRecyclerViewAdapter(context,mRecipeDatas,2));

                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG,error.toString());
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if (category != null) {
                    params.put("category", category);
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

        String category = adapterView.getItemAtPosition(i).toString().trim();

        fetchRecipeAccToCategories(this,category);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
