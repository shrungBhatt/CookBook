package com.example.jigsaw.cookbook;

import android.support.v7.app.AppCompatActivity;

import Listener.CallBackListener;
import Model.BaseModel;

/**
 * Created by jigsaw on 7/1/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements CallBackListener {


    @Override
    public abstract void handleSuccessData(BaseModel resModel);

    @Override
    public abstract void handleZeroData(BaseModel reqModel);

    @Override
    public void handleErrorDataFromServer(BaseModel errorModel) {

    }

    @Override
    public void networkConnectionError() {

    }

    @Override
    public void handleInvalidData(BaseModel reqModel) {

    }
}
