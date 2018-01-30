package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jigsaw.cookbook.R;

import java.util.List;

import Model.FeedBackData;

/**
 * Created by jigsaw on 30/1/18.
 */

public class FeedBack_RecyclerView_Adapter extends
        RecyclerView.Adapter<FeedBack_RecyclerView_Adapter.FeedBackViewHolder> {

    private Context mContext;
    private List<FeedBackData> mFeedBackDatas;

    public FeedBack_RecyclerView_Adapter(Context context,List<FeedBackData> feedBackDatas){
        mContext = context;
        mFeedBackDatas = feedBackDatas;
    }

    @Override
    public FeedBackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new FeedBackViewHolder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(FeedBackViewHolder holder, int position) {
        FeedBackData feedBackData = mFeedBackDatas.get(position);
        holder.bindFeedBackData(feedBackData);

    }

    @Override
    public int getItemCount() {
        return mFeedBackDatas.size();
    }

    class FeedBackViewHolder extends RecyclerView.ViewHolder {

        private TextView mRecipeName;
        private TextView mReview;
        private TextView mRating;
        private TextView mAuthor;
        private TextView mDate;


        FeedBackViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.feedback_list_item, parent, false));

            mRecipeName = itemView.findViewById(R.id.list_item_feedback_recipe_name);

            mReview = itemView.findViewById(R.id.list_item_feedback_review);

            mRating = itemView.findViewById(R.id.list_item_feedback_rating);

            mAuthor = itemView.findViewById(R.id.list_item_feedback_author);

            mDate = itemView.findViewById(R.id.list_item_feedback_date);


        }

        void bindFeedBackData(FeedBackData feedBackData){
            mRecipeName.setText(feedBackData.getmRecipeName());
            mReview.setText(feedBackData.getmReview());
            mRating.setText(feedBackData.getmRating());
            mAuthor.setText(feedBackData.getmAuthor());
            mDate.setText(feedBackData.getmDate());

        }


    }
}
