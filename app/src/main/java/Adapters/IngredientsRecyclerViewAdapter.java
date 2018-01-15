package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jigsaw.cookbook.R;

import java.util.List;

/**
 * Created by jigsaw on 15/1/18.
 */

public class IngredientsRecyclerViewAdapter extends
        RecyclerView.Adapter<IngredientsRecyclerViewAdapter.IngredientsViewHolder> {

    private Context mContext;
    private List<String> mArray;

    public IngredientsRecyclerViewAdapter(Context context,List<String> array){
        mContext = context;
        mArray = array;
    }


    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new IngredientsViewHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        holder.mIngredientName.setText(mArray.get(position));

    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder{

        private TextView mIngredientName;

        IngredientsViewHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.ingredient_list_item,parent,false));

            mIngredientName = itemView.findViewById(R.id.ingredients_recyclerview_list_item);



        }



    }
}
