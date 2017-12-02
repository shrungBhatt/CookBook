package com.example.jigsaw.cookbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private SearchView mIngredientSearchView;
    private List<String> mIngredientsList;
    private RecyclerView mRecipeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mIngredientSearchView = findViewById(R.id.enter_ingredient_search_view);

        mRecipeRecyclerView = findViewById(R.id.recipes_recyclerView);


    }


    private class RecipeHolder extends RecyclerView.ViewHolder{

        public RecipeHolder(View itemView) {
            super(itemView);
        }
    }

    private class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder>{

        @Override
        public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecipeHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}
