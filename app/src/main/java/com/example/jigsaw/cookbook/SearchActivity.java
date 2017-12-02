package com.example.jigsaw.cookbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private SearchView mIngredientSearchView;
    private List<String> mIngredientsList;
    private RecyclerView mRecipeRecyclerView;
    private RecipeData mRecipeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mIngredientSearchView = findViewById(R.id.enter_ingredient_search_view);

        mRecipeRecyclerView = findViewById(R.id.recipes_recyclerView);


    }


    private class RecipeHolder extends RecyclerView.ViewHolder{

        private TextView mRecipeNameTextView;
        private TextView mRecipeIngredientsTextView;

        public RecipeHolder(LayoutInflater layoutInflater,ViewGroup container) {
            super(layoutInflater.
                    inflate(R.layout.recipe_list_item,container,false));

            mRecipeNameTextView = itemView.findViewById(R.id.recipe_name_list_item_textView);

            mRecipeIngredientsTextView = itemView.findViewById(R.id.ingredients_list_item_textView);
        }

        void bindViewHolder(RecipeData recipeData){

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
            holder.bindViewHolder(recipeData);
        }

        @Override
        public int getItemCount() {
            return mRecipes.size();
        }
    }


    private void fetchRecipes(String query){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/Recipe.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG,"Got an error: "+error.toString());
                    }
                }){


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
