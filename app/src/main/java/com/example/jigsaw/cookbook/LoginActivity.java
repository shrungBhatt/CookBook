package com.example.jigsaw.cookbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import Model.BaseModel;
import Utility.MySharedPreferences;
import Utility.Utils;

/**
 * Created by jigsaw on 28/1/18.
 */


public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    public static Activity mActivity;
    public static Boolean mActive;
    private EditText mUserEmail;
    private EditText mUserPassword;
    private Button mLoginButton;
    private Button mSignUpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mActivity = this;

        boolean status = MySharedPreferences.getStoredLoginStatus(LoginActivity.this);
        if (status) {
            Intent i = new Intent(this, HomeScreen.class);
            startActivity(i);
        }


        mUserEmail = (EditText) findViewById(R.id.user_email);

        mUserPassword = (EditText) findViewById(R.id.user_password);

        mLoginButton = (Button) findViewById(R.id.email_sign_in_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String macId = Utils.getMacAddress(TAG);
                String emailId = mUserEmail.getText().toString();
                String pass = mUserPassword.getText().toString();

                if (!emailId.equals("") || !pass.equals("")) {
                    requestLogin(macId, emailId, pass);
                } else {
                    mUserEmail.setError("Fill it up");
                    mUserPassword.setError("Fill it up");
                }

            }
        });

        mSignUpButton = (Button) findViewById(R.id.user_sign_up_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Activity_Signup.class);
                startActivity(i);
                finish();

            }
        });


    }

    private void requestLogin(final String mac_id, final String userName, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/cook_book_login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            if (response != null &&
                                    !response.equals("Wrong Username or Password")) {
                                MySharedPreferences.setStoredLoginStatus(LoginActivity.this, true);
                                MySharedPreferences.setStoredUsername(LoginActivity.this,userName);
                                Intent i = new Intent(LoginActivity.this, HomeScreen.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT)
                                        .show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("mac_id", mac_id);
                params.put("username", userName);
                params.put("password", password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    @Override
    public void handleSuccessData(BaseModel resModel) {

    }

    @Override
    public void handleZeroData(BaseModel reqModel) {

    }

    @Override
    public void onStart() {
        super.onStart();
        mActive = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        mActive = false;
    }

}
