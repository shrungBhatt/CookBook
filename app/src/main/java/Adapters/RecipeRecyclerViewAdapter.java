package Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jigsaw.cookbook.R;
import Model.RecipeData;
import com.example.jigsaw.cookbook.RecipePagerActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import Utility.RoundedTransformation;

/**
 * Created by jigsaw on 14/1/18.
 */

public class RecipeRecyclerViewAdapter extends
        RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeViewHolder> {

    private Context mContext;
    private List<RecipeData> mRecipeDatas;
    private int mActivityId;

    public RecipeRecyclerViewAdapter(Context context, List<RecipeData> recipeDatas,int activityId){
        mContext = context;
        mRecipeDatas = recipeDatas;
        mActivityId = activityId;
    }



    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new RecipeViewHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        final RecipeData recipeData = mRecipeDatas.get(position);

        holder.bindRecipesData(recipeData);
        holder.bindRecipeImage(recipeData,mContext);
        holder.mFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = RecipePagerActivity.newIntent(mContext,recipeData.getId(),mActivityId);
                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mRecipeDatas.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder{

        private FrameLayout mFrameLayout;
        private ImageView mRecipeImageView;
        private TextView mRecipeNameTextView;
        private TextView mIngredientsTextView;


        RecipeViewHolder(LayoutInflater inflater, ViewGroup container){
            super(inflater.inflate(R.layout.recipe_list_item,container,false));

            mFrameLayout = itemView.findViewById(R.id.recipe_select_button);

            mRecipeImageView = itemView.findViewById(R.id.recipe_image_image_view);

            mRecipeNameTextView = itemView.findViewById(R.id.recipe_name_list_item_textView);

            mIngredientsTextView = itemView.findViewById(R.id.ingredients_list_item_textView);
        }



        void bindRecipesData(RecipeData recipeData){
            mRecipeNameTextView.setText(recipeData.getRecipeName());
            mIngredientsTextView.setText(recipeData.getRecipeIngredients());

        }

        void bindRecipeImage(RecipeData recipeData,Context context){
            try {
                Picasso.with(context).load(recipeData.getmImageUrl())
                        .transform(new RoundedTransformation(50, 4))
                        .placeholder(R.drawable.progress_animation).into(mRecipeImageView);
            }catch (Exception e){
                e.printStackTrace();
                mRecipeImageView.setScaleType(ImageView.ScaleType.CENTER);
                mRecipeImageView.setImageResource(R.drawable.ic_image_error);
            }
        }



    }


}
