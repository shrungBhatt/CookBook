package Model;

/**
 * Created by jigsaw on 30/1/18.
 */

public class FeedBackData extends BaseModel {

    private String mRecipeName;
    private String mReview;
    private String mAuthor;
    private String mDate;
    private String mRating;
    private String mMacId;
    private String mId;

    public String getmMacId() {
        return mMacId;
    }

    public void setmMacId(String mMacId) {
        this.mMacId = mMacId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmRecipeName() {
        return mRecipeName;
    }

    public void setmRecipeName(String mRecipeName) {
        this.mRecipeName = mRecipeName;
    }

    public String getmReview() {
        return mReview;
    }

    public void setmReview(String mReview) {
        this.mReview = mReview;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }
}
