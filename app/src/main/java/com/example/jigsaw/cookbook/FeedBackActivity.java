package com.example.jigsaw.cookbook;

import android.os.Bundle;
import android.support.annotation.Nullable;

import Model.BaseModel;

/**
 * Created by jigsaw on 30/1/18.
 */

public class FeedBackActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }
}
