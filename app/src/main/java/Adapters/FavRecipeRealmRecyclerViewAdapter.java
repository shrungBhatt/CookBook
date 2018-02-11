package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jigsaw.cookbook.R;
import com.squareup.picasso.Picasso;

import Model.RecipeDataRealm;
import Utility.RoundedTransformation;
import io.realm.Realm;
import realm.RealmController;

/**
 * Created by jigsaw on 14/1/18.
 */

public class FavRecipeRealmRecyclerViewAdapter extends
        RealmRecyclerViewAdapter<RecipeDataRealm> {

    private Context mContext;
    private Realm mRealm;

    public FavRecipeRealmRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new RecipeViewHolder(inflater,parent);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        mRealm = RealmController.getInstance().getRealm();

        final RecipeDataRealm recipeDataRealm = getItem(position);

        final RecipeViewHolder recipeViewHolder = (RecipeViewHolder) holder;

        recipeViewHolder.bindRecipesData(recipeDataRealm);
        recipeViewHolder.bindRecipeImage(recipeDataRealm,mContext);

    }

    @Override
    public int getItemCount() {

        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }


    class RecipeViewHolder extends RecyclerView.ViewHolder {

        private FrameLayout mFrameLayout;
        private ImageView mRecipeImageView;
        private TextView mRecipeNameTextView;
        private TextView mIngredientsTextView;


        RecipeViewHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.recipe_list_item, container, false));

            mFrameLayout = itemView.findViewById(R.id.recipe_select_button);

            mRecipeImageView = itemView.findViewById(R.id.recipe_image_image_view);

            mRecipeNameTextView = itemView.findViewById(R.id.recipe_name_list_item_textView);

            mIngredientsTextView = itemView.findViewById(R.id.ingredients_list_item_textView);
        }


        void bindRecipesData(RecipeDataRealm recipeDataRealm) {
            mRecipeNameTextView.setText(recipeDataRealm.getRecipeName());
            mIngredientsTextView.setText(recipeDataRealm.getRecipeIngredients());

        }

        void bindRecipeImage(RecipeDataRealm recipeDataRealm, Context context) {
            try {
                Picasso.with(context).load(recipeDataRealm.getImageUrl())
                        .transform(new RoundedTransformation(50, 4))
                        .placeholder(R.drawable.ic_image_error).into(mRecipeImageView);
            } catch (Exception e) {
                e.printStackTrace();
                mRecipeImageView.setImageResource(R.drawable.ic_image_error);
            }
        }


    }


}
