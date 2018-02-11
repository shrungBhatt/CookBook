package com.example.jigsaw.cookbook;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import Model.BaseModel;
import Utility.MySharedPreferences;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jigsaw on 9/1/18.
 */

public class HomeScreen extends BaseActivity {

    private final String TAG = "HomeScreen";

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


        try {
            if (MySharedPreferences.getStoredLoginStatus(this) &&
                    LoginActivity.mActive) {

                LoginActivity.mActivity.finish();
            }
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }

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
                startActivity(new Intent(this,RecipeCuisinesCategory.class));
                break;

            case R.id.categories_button:
                startActivity(new Intent(this,RecipeCategoriesActivity.class));
                break;

            case R.id.favourite_recipes_button:
                intent = new Intent(this,FavouriteRecipeActivityRealm.class);
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

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out_menu_item) {
            if(isNetworkAvailableAndConnected()){
                MySharedPreferences.setStoredLoginStatus(HomeScreen.this,false);
                Intent i = new Intent(HomeScreen.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            }
            return true;
        }else if(id == R.id.add_recipe_menu_item){
            startActivity(new Intent(this,AddRecipeActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailableAndConnected () {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;

        return isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();
    }
}
