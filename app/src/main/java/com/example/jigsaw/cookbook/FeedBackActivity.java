package com.example.jigsaw.cookbook;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import Adapters.FeedBack_RecyclerView_Adapter;
import Model.BaseModel;
import Model.FeedBackData;
import Utility.JSONParser;
import Utility.MySharedPreferences;
import Utility.SimpleDividerItemDecoration;

/**
 * Created by jigsaw on 30/1/18.
 */

public class FeedBackActivity extends BaseActivity {


    private List<FeedBackData> mFeedBackDatas;
    private RecyclerView mRecyclerView;
    private String TAG = "FeedBackActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        try {
            if (MySharedPreferences.getStoredLoginStatus(this) &&
                    LoginActivity.mActive) {

                LoginActivity.mActivity.finish();
            }
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }

        mRecyclerView = findViewById(R.id.activity_feedback_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        fetchFeedBackData(this);

    }

    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }


    private void fetchFeedBackData(final Context context){

        showProgressBar(context,TAG);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/fetch_recipe_feedback_data.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideProgressBar();
                            mFeedBackDatas = JSONParser.getFeedBackDatas(response);

                            if(mFeedBackDatas.size() != 0){
                                mRecyclerView.setAdapter(new FeedBack_RecyclerView_Adapter(context,
                                        mFeedBackDatas));
                            }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgressBar();
                Log.e(TAG, error.toString());

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home_screen_menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out_menu_item) {
            if(isNetworkAvailableAndConnected()){
                MySharedPreferences.setStoredLoginStatus(FeedBackActivity.this,false);
                MySharedPreferences.setIsAdminLoggedOn(FeedBackActivity.this,false);
                Intent i = new Intent(FeedBackActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            }
            return true;
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
