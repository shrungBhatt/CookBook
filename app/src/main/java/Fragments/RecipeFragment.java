package Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jigsaw.cookbook.FeedBackDialog;
import com.example.jigsaw.cookbook.R;
import com.example.jigsaw.cookbook.RecipePagerActivity;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Adapters.IngredientsRecyclerViewAdapter;
import Model.RecipeData;
import Model.RecipeDataRealm;
import Utility.PDFGenerator;
import Utility.SimpleDividerItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import realm.RealmController;

/**
 * Created by jigsaw on 15/1/18.
 */

public class RecipeFragment extends BaseFragment {

    private static final String ARG_ARRAY_POSITION = "Array position";
    private static final String ARG_RECIPE_ID = "Recipe Id";

    @BindView(R.id.recipe_fragment_recipe_image_imageview)
    ImageView mRecipeImageImageView;

    @BindView(R.id.recipe_fragment_recipe_name_textview)
    TextView mRecipeNameTextView;

    @BindView(R.id.recipe_fragment_ingredients_drop_down)
    CardView mRecipeDropdownButton;

    @BindView(R.id.recipe_fragment_recipe_overview_textview)
    TextView mRecipeOverviewTextView;

    @BindView(R.id.recipe_fragment_pdf_btn)
    CardView mPdfButton;

    @BindView(R.id.recipe_fragment_ingredients_recycler_view)
    RecyclerView mIngredientsRecyclerView;

    @BindView(R.id.recipe_fragment_feedback_btn)
    CardView mFeedBackButton;

    @BindView(R.id.recipe_frament_recipe_rating)
    RatingBar mRecipeRatingBar;

    @BindView(R.id.show_ingredients_button)
    ImageView mShowMoreButton;
    @BindView(R.id.recipe_fav_button)
    FloatingActionButton recipeFavButton;

    private int mRecipeId;
    private int mArrayPosition;
    private List<RecipeData> mRecipeDatas;
    private List<String> mIngredients;
    private boolean mToogle = false;
    private Realm mRealm;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    public static RecipeFragment newInstance(int recipeId, int position) {
        Bundle args = new Bundle();
        args.putInt(ARG_RECIPE_ID, recipeId);
        args.putInt(ARG_ARRAY_POSITION, position);
        RecipeFragment fragment = new RecipeFragment();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, v);

        mRealm = RealmController.with(getActivity()).getRealm();

        mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
        mArrayPosition = getArguments().getInt(ARG_ARRAY_POSITION);
        mRecipeDatas = RecipePagerActivity.mRecipeDatas;

        mRecipeNameTextView.setText(mRecipeDatas.get(mArrayPosition).getRecipeName());

        mRecipeOverviewTextView.setText(mRecipeDatas.get(mArrayPosition).getmRecipeOverview());

        mRecipeRatingBar.setRating(mRecipeDatas.get(mArrayPosition).getmRecipeRating());

        mIngredients = Arrays.
                asList(mRecipeDatas.get(mArrayPosition).getRecipeIngredients().split(","));
        populateRecyclerView();


        try {
            Picasso.with(getActivity()).load(mRecipeDatas.get(mArrayPosition).getmImageUrl())
                    .placeholder(R.drawable.progress_animation).into(mRecipeImageImageView);
        } catch (Exception e) {
            e.printStackTrace();
            mRecipeImageImageView.setScaleType(ImageView.ScaleType.CENTER);
            mRecipeImageImageView.setImageResource(R.drawable.ic_image_error);
        }


        final Animation rotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        rotate.setRepeatCount(1);
        rotate.setFillAfter(true);

        final Animation reverseRotate = AnimationUtils.
                loadAnimation(getActivity(), R.anim.reverse_rotate);
        reverseRotate.setRepeatCount(1);
        reverseRotate.setFillAfter(true);


        mRecipeDropdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mToogle) {
                    mShowMoreButton.startAnimation(rotate);
                    mIngredientsRecyclerView.setVisibility(View.VISIBLE);
                    mToogle = true;
                } else {
                    mShowMoreButton.startAnimation(reverseRotate);
                    mIngredientsRecyclerView.setVisibility(View.GONE);
                    mToogle = false;
                }


            }
        });

        mFeedBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedBackDialog feedBackDialog = new FeedBackDialog(getActivity(),
                        mRecipeDatas.get(mArrayPosition).getRecipeName());
                feedBackDialog.show();
            }
        });

        mPdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = mRecipeDatas.get(mArrayPosition).getRecipeName() +
                        " Ingredients";

                PDFGenerator.write(fileName, mIngredients, getActivity());

            }
        });

        recipeFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecipeDataRealm recipeDataRealm = new RecipeDataRealm();
                recipeDataRealm.setId(new Random().nextInt(1000));
                recipeDataRealm.setRecipeName(mRecipeNameTextView.getText().toString());
                recipeDataRealm.setRecipeCategory(mRecipeDatas.get(mArrayPosition).getmRecipeCategory());
                recipeDataRealm.setRecipeCuisine(mRecipeDatas.get(mArrayPosition).getmRecipeCuisine());
                recipeDataRealm.setRecipeRating(mRecipeRatingBar.getRating());
                recipeDataRealm.setRecipeOverview(mRecipeOverviewTextView.getText().toString());
                if(!mRecipeDatas.get(mArrayPosition).getmImageUrl().isEmpty()){
                    recipeDataRealm.setImageUrl(mRecipeDatas.get(mArrayPosition).getmImageUrl());
                }
                recipeDataRealm.setRecipeIngredients(mRecipeDatas.get(mArrayPosition).getRecipeIngredients());


                mRealm.beginTransaction();
                mRealm.copyToRealm(recipeDataRealm);
                mRealm.commitTransaction();

                Toast.makeText(getActivity(),"Added to favourites",Toast.LENGTH_SHORT).show();

            }
        });

        return v;


    }


    void populateRecyclerView() {

        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIngredientsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        if (mIngredients.size() != 0) {
            mIngredientsRecyclerView.setAdapter(new IngredientsRecyclerViewAdapter(getActivity(), mIngredients));
        }

    }


}
