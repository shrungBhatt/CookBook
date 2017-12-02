package com.example.jigsaw.cookbook;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private SearchView mIngredientSearchView;
    private List<String> mIngredientsList;
    private RecyclerView mRecipeRecyclerView;
    private RecipeData mRecipeData;
    private List<RecipeData> mRecipeDatasRedundant;
    private List<RecipeData> mRecipeDatas;
    private EditText mSearchEditText;
    private ImageButton mSearchImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        mIngredientSearchView = findViewById(R.id.enter_ingredient_search_view);

        mSearchEditText = findViewById(R.id.search_ingredients_edit_text);

        mSearchImageButton = findViewById(R.id.search_ingredients_image_button);
        mSearchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchRecipes(mSearchEditText.getText().toString());
            }
        });

        mRecipeRecyclerView = findViewById(R.id.recipes_recyclerView);
        mRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }


    private class RecipeHolder extends RecyclerView.ViewHolder{

        private TextView mRecipeNameTextView;
        private TextView mRecipeIngredientsTextView;

        RecipeHolder(LayoutInflater layoutInflater, ViewGroup container) {
            super(layoutInflater.
                    inflate(R.layout.recipe_list_item,container,false));

            mRecipeNameTextView = itemView.findViewById(R.id.recipe_name_list_item_textView);

            mRecipeIngredientsTextView = itemView.findViewById(R.id.ingredients_list_item_textView);
        }

        void recipeViewHolder(RecipeData recipeData){

            mRecipeData = recipeData;
            mRecipeNameTextView.setText(mRecipeData.getRecipeName());
            mRecipeIngredientsTextView.setText(mRecipeData.getRecipeIngredients());
        }
    }

    private class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder>{

        private List<RecipeData> mRecipes;

        RecipeAdapter(List<RecipeData> recipes){
            mRecipes = recipes;
        }

        @Override
        public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            return new RecipeHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(RecipeHolder holder, int position) {
            RecipeData recipeData = mRecipes.get(position);
            holder.recipeViewHolder(recipeData);
        }

        @Override
        public int getItemCount() {
            return mRecipes.size();
        }
    }


    private void fetchRecipes(final String query){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/recipe.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mRecipeDatasRedundant = getRecipeDatas(response);
                        mRecipeRecyclerView.setAdapter(new RecipeAdapter(mRecipeDatasRedundant));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG,"Got an error: "+error.toString());
                    }
                }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if (query != null) {
                    params.put("ingredient", query);
                }
                return params;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private List<RecipeData> getRecipeDatas(String result){

        List<RecipeData> recipeDatas = new ArrayList<>();

        try{

            JSONArray jsonArray = new JSONArray(result);

            for(int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                RecipeData recipeData = new RecipeData();

                recipeData.setId(Integer.valueOf(jsonObject.getString("id")));
                recipeData.setRecipeName(jsonObject.getString("recipe"));
                recipeData.setRecipeIngredients(jsonObject.getString("ingredient"));

                recipeDatas.add(recipeData);

            }

        }catch(JSONException e){
            e.printStackTrace();
        }

        return recipeDatas;
    }

}
