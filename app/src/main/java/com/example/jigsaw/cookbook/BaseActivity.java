package com.example.jigsaw.cookbook;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import Listener.CallBackListener;
import Model.BaseModel;

/**
 * Created by jigsaw on 7/1/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements CallBackListener {


    private Dialog mProgressDialog;

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

    public void makeToast(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }



    public void showProgressBar(Context context,String TAG) {
        mProgressDialog = new Dialog(context);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.setContentView(R.layout.circleprogress);
        mProgressDialog.setCancelable(false);

        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mProgressDialog.getWindow().setGravity(Gravity.CENTER);
        try {
            mProgressDialog.show();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void hideProgressBar() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        } catch (Exception e) {
            Log.e("BaseClassForInterface", "Error in hideProgressBar");
        }
    }


    public void addRecipe(View view){



    }
}
