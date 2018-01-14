package com.example.jigsaw.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import Model.BaseModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jigsaw on 9/1/18.
 */

public class HomeScreen extends BaseActivity {

    @BindView(R.id.all_recipes_button)
    FrameLayout mAllRecipeButton;

    @BindView(R.id.cuisines_button)
    FrameLayout mCuisinesButton;

    @BindView(R.id.categories_button)
    FrameLayout mCategoriesButton;

    @BindView(R.id.favourite_recipes_button)
    FrameLayout mFavouritesButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);


    }


    @OnClick({R.id.all_recipes_button, R.id.cuisines_button, R.id.categories_button,
            R.id.favourite_recipes_button})
    public void onViewClicked(View view) {

        Intent intent = new Intent(this,AllRecipeActivity.class);

        switch (view.getId()) {
            case R.id.all_recipes_button:
                startActivity(intent);
                break;

            case R.id.cuisines_button:
                makeToast(this,"Cuisines Button Clicked");
                break;

            case R.id.categories_button:
                makeToast(this,"Categories Button Clicked");
                break;

            case R.id.favourite_recipes_button:
                intent = new Intent(this,FavouriteRecipeActivity.class);
                startActivity(intent);
                break;
        }


    }

    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }
}
