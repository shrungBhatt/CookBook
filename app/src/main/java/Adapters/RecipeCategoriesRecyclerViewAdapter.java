package Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jigsaw.cookbook.R;

import java.util.List;

/**
 * Created by jigsaw on 17/1/18.
 */

public class RecipeCategoriesRecyclerViewAdapter extends
        RecyclerView.Adapter<RecipeCategoriesRecyclerViewAdapter.RecipeCategoryViewHolder>{

    private Context mContext;
    private List<String> mArray;

    public RecipeCategoriesRecyclerViewAdapter(Context context, List<String> array){
        mContext = context;
        mArray = array;
    }


    @Override
    public RecipeCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new RecipeCategoryViewHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(RecipeCategoryViewHolder holder, int position) {
        holder.mTextView.setText(mArray.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

    class RecipeCategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextView;


        RecipeCategoryViewHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.recipe_category_recyclerview_list_item,parent,
                    false));

            mTextView = itemView.findViewById(R.id.recipe_category_recyclerview_list_item);

        }


    }
}
