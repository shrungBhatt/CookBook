package Controller;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;

import com.example.jigsaw.cookbook.BaseActivity;
import com.example.jigsaw.cookbook.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import Fragments.BaseFragment;
import Listener.CallBackListener;
import Model.BaseModel;
import Model.ErrorModel;
import Parser.BaseParser;
import Retrofit.APIManager;
import Retrofit.RetrofitAdapter;
import Utility.MyEnum;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jigsaw on 7/1/18.
 */

public abstract class BaseController {

    private static final String TAG = "BaseController";

    protected MyEnum.ShowProgressbar showProgress = MyEnum.ShowProgressbar.show;
    CallBackListener mCallBackListener;
    RetrofitAdapter mRetrofitAdapter;
    APIManager mApiManager;
    BaseModel mBaseModel;
    BaseParser mBaseParser;
    Dialog mProgressDialog;
    Context mContext;


    Callback<String> mRetrofitCallback = new Callback<String>() {
        @Override
        public void success(String s, Response response) {
            //Carry out tasks when the response is received after a success
            hideProgressBar();
        }

        @Override
        public void failure(RetrofitError error) {
            //Carry out task if any failure occurs while the request is being carried out.
            hideProgressBar();


        }
    };

    public void startFetching(CallBackListener callBackListener, BaseModel baseModel) {
        this.mCallBackListener = callBackListener;
        mRetrofitAdapter = new RetrofitAdapter();
        if (showProgress == MyEnum.ShowProgressbar.show) {
            showProgressBar();
        }
        mApiManager = mRetrofitAdapter.getmApiManager();
    }

    public abstract void onPopulate(JSONObject jsonObject, BaseParser baseParser);

    private void showProgressBar() {
        if (mCallBackListener instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) mCallBackListener;
            mContext = baseActivity.getApplicationContext();
            this.mProgressDialog = new Dialog(baseActivity);
        } else if (mCallBackListener instanceof BaseFragment) {
            BaseFragment baseFragment = (BaseFragment) mCallBackListener;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mContext = baseFragment.getContext();
                this.mProgressDialog = new Dialog(baseFragment.getActivity());
            }


        }
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

    private void hideProgressBar() {
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

    public void showInvalidData(BaseModel reqModel) {
        mCallBackListener.handleInvalidData(reqModel);
    }

    private void showErrorFromResponse(JSONObject jsonObject) {
        ErrorModel errorModel;
        Gson gson = new Gson();
        errorModel = gson.fromJson(jsonObject.toString(), ErrorModel.class);
        mCallBackListener.handleErrorDataFromServer(errorModel);
    }

}
