package com.example.jigsaw.cookbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    //    private SearchView mIngredientSearchView;
    private List<String> mIngredientsList;
    private RecyclerView mRecipeRecyclerView;
    private RecipeData mRecipeData;
    private List<RecipeData> mRecipeDatasRedundant;
    private List<RecipeData> mRecipeDatas;
    private ProgressWheel mProgressWheel;
//    private EditText mSearchEditText;
//    private ImageButton mSearchImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //This is used to change the color of the app name in the action bar.
//        getSupportActionBar().
//                setTitle(Html.fromHtml("<font color=\"#f8a300\">" +
//                        getString(R.string.app_name) + "</font>"));


//        mIngredientSearchView = findViewById(R.id.enter_ingredient_search_view);

//        mSearchEditText = findViewById(R.id.search_ingredients_edit_text);
//
//        mSearchImageButton = findViewById(R.id.search_ingredients_image_button);
//        mSearchImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fetchRecipes(mSearchEditText.getText().toString());
//            }
//        });

        //RecyclerView to display the recipes fetched from the database.
        mRecipeRecyclerView = findViewById(R.id.recipes_recyclerView);
        //Setting the layout of the recyclerView as linear.
        mRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        mRecipeRecyclerView.
//                addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
//        mProgressWheel = findViewById(R.id.progress_wheel);

        fetchRecipes(",");
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
                fetchRecipes(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                fetchRecipes(s);
                return true;
            }
        });
        return true;
    }


    //Class used to define the viewHolder of the recyclerView.
    private class RecipeHolder extends RecyclerView.ViewHolder {

        private TextView mRecipeNameTextView;
        private TextView mRecipeIngredientsTextView;
        private ImageView mRecipeImageView;

        //Constructor called in the adapter class.
        RecipeHolder(LayoutInflater layoutInflater, ViewGroup container) {
            super(layoutInflater.
                    inflate(R.layout.recipe_list_item, container, false));

            mRecipeNameTextView = itemView.findViewById(R.id.recipe_name_list_item_textView);

            mRecipeIngredientsTextView = itemView.findViewById(R.id.ingredients_list_item_textView);

            mRecipeImageView = itemView.findViewById(R.id.recipe_image_image_view);
        }

        //Method used in adapter to bind the data in the viewHolder.
        void recipeViewHolder(RecipeData recipeData) {
            mRecipeData = recipeData;
            mRecipeNameTextView.setText(mRecipeData.getRecipeName());
            mRecipeIngredientsTextView.setText(mRecipeData.getRecipeIngredients());
        }

        //Method used in adapter to download the image from the server and load it into the
        //recycler view using Picasso API.
        void bindRecipeImage(RecipeData recipeData) {
            Picasso.with(getApplicationContext()).load(recipeData.getmImageUrl())
                    .transform(new RoundedTransformation(50,4))
                    .into(mRecipeImageView);
        }
    }


    //Adapter class used for recyclerView.
    private class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder> {

        private List<RecipeData> mRecipes;

        RecipeAdapter(List<RecipeData> recipes) {
            mRecipes = recipes;
        }

        @Override
        public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            return new RecipeHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(RecipeHolder holder, int position) {
            RecipeData recipeData = mRecipes.get(position);
            holder.recipeViewHolder(recipeData);
            holder.bindRecipeImage(recipeData);
        }

        @Override
        public int getItemCount() {
            return mRecipes.size();
        }
    }


    //method using Volley to fetch the data from the database.
    private void fetchRecipes(final String query) {

        //Building the request to be send to the server.
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/recipe.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Parsing the JSON string that we received in the response of the request.
                        mRecipeDatasRedundant = getRecipeDatas(response);

                        //Setting the recyclerView with the ArrayList that we obtained by parsing
                        //the response string.
                        mRecipeRecyclerView.setAdapter(new RecipeAdapter(mRecipeDatasRedundant));
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
    private List<RecipeData> getRecipeDatas(String result) {

        List<RecipeData> recipeDatas = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(result);
            //Loop used for storing the data in the ArrayList, by parsing the response string.
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                RecipeData recipeData = new RecipeData();

                recipeData.setId(Integer.valueOf(jsonObject.getString("id")));
                recipeData.setRecipeName(jsonObject.getString("recipe"));
                recipeData.setRecipeIngredients(jsonObject.getString("ingredient"));
                recipeData.setmImageUrl(jsonObject.getString("image_url"));

                recipeDatas.add(recipeData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeDatas;
    }


}
