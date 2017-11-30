package com.example.jigsaw.cookbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;


import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private SearchView mIngredientSearchView;
    private List<String> mIngredientsList;
    private Button mParseStringButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mIngredientSearchView = findViewById(R.id.enter_ingredient_search_view);

        mParseStringButton = findViewById(R.id.parse_search_string_button);
        mParseStringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIngredientsList = parseString();
                for(String ingredient : mIngredientsList){
                    Log.i(TAG,ingredient);
                }
            }
        });

    }

    private List<String> parseString(){
        String ingredientsString = mIngredientSearchView.getQuery().toString().trim();
        return Arrays.asList(ingredientsString.split(","));
    }
}
