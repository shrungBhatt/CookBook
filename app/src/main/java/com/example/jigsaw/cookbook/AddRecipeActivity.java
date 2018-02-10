package com.example.jigsaw.cookbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import Model.BaseModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jigsaw on 7/2/18.
 */

public class AddRecipeActivity extends BaseActivity implements AdapterView.OnItemSelectedListener{

    public static final String TAG = "AddRecipeActivity";

    @BindView(R.id.add_recipe_name_edt)
    AutoCompleteTextView addRecipeNameEdt;
    @BindView(R.id.add_recipe_overview_edt)
    AutoCompleteTextView addRecipeOverviewEdt;
    @BindView(R.id.add_recipe_ingredients_edt)
    AutoCompleteTextView addRecipeIngredientsEdt;
    @BindView(R.id.add_recipe_categories_spinner)
    Spinner addRecipeCategoriesSpinner;
    @BindView(R.id.add_recipe_cuisine_spinner)
    Spinner addRecipeCuisineSpinner;
    @BindView(R.id.add_recipe_fab_button)
    FloatingActionButton addRecipeFabButton;


    private String mRecipeCategory;
    private String mRecipeCuisine;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        ButterKnife.bind(this);


        init();

    }

    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }

    private void init() {

        setAdapter(R.array.type_of_categories, addRecipeCategoriesSpinner);
        setAdapter(R.array.type_of_cuisines,addRecipeCuisineSpinner);

    }


    private void setAdapter(int id, Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                id, R.layout.simple_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @OnClick({R.id.add_recipe_fab_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_recipe_fab_button:
                addRecipe(this,addRecipeNameEdt.getText().toString(),
                        addRecipeIngredientsEdt.getText().toString().trim(),
                        mRecipeCategory,
                        mRecipeCuisine,
                        addRecipeOverviewEdt.getText().toString());
                break;
        }
    }


    private void addRecipe(final Context context, final String recipeName,
                           final String ingredients,
                           final String category,
                           final String cuisine,
                           final String overview) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/add_recipe_data.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Insert SuccessFul")) {
                            Toast.makeText(getApplicationContext(),
                                    "Recipe Added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context,HomeScreen.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("recipe", recipeName);
                params.put("ingredients",ingredients);
                params.put("category",category);
                params.put("cuisine",cuisine);
                params.put("overview",overview);
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()){

            case R.id.add_recipe_categories_spinner:
                mRecipeCategory = adapterView.getSelectedItem().toString().trim();
                break;

            case R.id.add_recipe_cuisine_spinner:
                mRecipeCuisine = adapterView.getSelectedItem().toString().trim();
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
