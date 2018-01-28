package com.example.jigsaw.cookbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

import Fragments.RecipeFragment;
import Model.BaseModel;
import Utility.JSONParser;
import Utility.MySharedPreferences;

/**
 * Created by jigsaw on 15/1/18.
 */

public class RecipePagerActivity extends BaseActivity {


    private static final String EXTRA_ACTIVITY_ID = "Activity Id";
    private static String EXTRA_RECIPE_ID = "Recipe Id";

    private ViewPager mViewPager;
    public static List<RecipeData> mRecipeDatas;
    public static List<RecipeData> mFavrtRecipeDatas;


    public static Intent newIntent(Context context, int id,int activityId) {
        Intent intent = new Intent(context, RecipePagerActivity.class);
        intent.putExtra(EXTRA_RECIPE_ID, id);
        intent.putExtra(EXTRA_ACTIVITY_ID,activityId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view_pager);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        int recipeId = (int) getIntent().getSerializableExtra(EXTRA_RECIPE_ID);
        int activityId = (int) getIntent().getSerializableExtra(EXTRA_ACTIVITY_ID);


        mViewPager = findViewById(R.id.activity_recipe_view_pager);
        mViewPager.setOffscreenPageLimit(4);



        switch (activityId){
            case 0:
                mRecipeDatas = JSONParser.
                        getRecipeDatas(MySharedPreferences.getAllRecipeArrayJson(this));
                break;

            case 1:
                mRecipeDatas = JSONParser.
                        getRecipeDatas(MySharedPreferences.getFavrtRecipeArrayJson(this));
                break;

            case 2:
                mRecipeDatas = JSONParser.
                        getRecipeDatas(MySharedPreferences.getRecipeDatasByCategory(this));
                break;

            case 3:
                mRecipeDatas = JSONParser.
                        getRecipeDatas(MySharedPreferences.getRecipeDatasByCuisines(this));
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {//For interaction with the viewPager
            @Override
            public Fragment getItem(int position) { //To make the magic happen which is to set the selected row to display
                RecipeData recipeData1 = mRecipeDatas.get(position);
                return RecipeFragment.newInstance(recipeData1.getId(),position);
            }

            @Override
            public int getCount() {
                return mRecipeDatas.size();
            }
        });

        for (int i = 0; i < mRecipeDatas.size(); i++) {
            if (mRecipeDatas.get(i).getId() == recipeId) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }


    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }
}
