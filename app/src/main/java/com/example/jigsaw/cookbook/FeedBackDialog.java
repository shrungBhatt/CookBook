package com.example.jigsaw.cookbook;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
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
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jigsaw on 30/1/18.
 */

public class FeedBackDialog extends Dialog {

    private static final String TAG = "FeedBackActivity";

    private String mRecipeName;
    private Context mContext;

    @BindView(R.id.close_dialog_button)
    ImageView mCloseDialogBtn;

    @BindView(R.id.feedback_recipe_name)
    TextView mRecipeNameTextView;

    @BindView(R.id.submit_button)
    CardView mSubmitBtn;

    @BindView(R.id.enter_review_edit_text)
    EditText mFeedBackEditText;

    @BindView(R.id.feedback_rating_textview)
    TextView mRatingTextView;

    @BindView(R.id.recipe_feedback_rating)
    RatingBar mRatingBar;

    public FeedBackDialog(@NonNull Context context, String recipeName) {
        super(context);
        mRecipeName = recipeName;
        mContext = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.feedback_dialog);
        ButterKnife.bind(this);


        mCloseDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mRecipeNameTextView.setText(mRecipeName);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingTextView.setText(String.valueOf(ratingBar.getRating()));
            }
        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String macId = Utils.getMacAddress(TAG);
                String userName = MySharedPreferences.getStoredUsername(mContext);
                String recipeName = mRecipeName;
                String review = mFeedBackEditText.getText().toString();
                String rating = String.valueOf(mRatingBar.getRating());

                submitFeedback(mContext,macId,userName,recipeName,review,rating);
            }
        });

    }


    private void submitFeedback(Context context,final String macId,final String username,
                                final String recipeName,final String review,
                                final String rating){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://ersnexus.esy.es/submit_recipe_feedback.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("1")){
                            dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("mac_id",macId);
                params.put("username",username);
                params.put("recipe_name",recipeName);
                params.put("review",review);
                params.put("rating",rating);
                return params;
            }



        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);


    }


}
