package com.example.jigsaw.cookbook;

import android.os.Bundle;

import Model.BaseModel;

/**
 * Created by jigsaw on 9/1/18.
 */

public class HomeScreen extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


    }


    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }
}
