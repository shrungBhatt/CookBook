package Model;

//This is the model class used to manage the data of the Recipe.
//It consists of getters and setters.

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

public class RecipeData extends BaseModel{

    @SerializedName("id")
    private int mId;
    @SerializedName("recipe")
    private String mRecipeName;
    @SerializedName("ingredients")
    private String mRecipeIngredients;
    @SerializedName("image_url")
    private String mImageUrl;
    private String mRecipeCategory;
    private String mRecipeCuisine;
    private int mIsRecipeFav;
    private static List<RecipeData> mRecipeDatas;
    private String mRecipeOverview;
    private float mRecipeRating;
    private static JSONObject mJSONObject;

    public static JSONObject getmJSONObject() {
        return mJSONObject;
    }

    public static void setmJSONObject(JSONObject mJSONObject) {
        RecipeData.mJSONObject = mJSONObject;
    }

    public String getmRecipeOverview() {
        return mRecipeOverview;
    }

    public void setmRecipeOverview(String mRecipeOverview) {
        this.mRecipeOverview = mRecipeOverview;
    }

    public float getmRecipeRating() {
        return mRecipeRating;
    }

    public void setmRecipeRating(float mRecipeRating) {
        this.mRecipeRating = mRecipeRating;
    }

    public static List<RecipeData> getmRecipeDatas() {
        return mRecipeDatas;
    }

    public static void setmRecipeDatas(List<RecipeData> recipeDatas) {
        mRecipeDatas = recipeDatas;
    }

    public String getmRecipeCategory() {
        return mRecipeCategory;
    }

    public void setmRecipeCategory(String mRecipeCategory) {
        this.mRecipeCategory = mRecipeCategory;
    }

    public String getmRecipeCuisine() {
        return mRecipeCuisine;
    }

    public void setmRecipeCuisine(String mRecipeCuisine) {
        this.mRecipeCuisine = mRecipeCuisine;
    }

    public int getmIsRecipeFav() {
        return mIsRecipeFav;
    }

    public void setmIsRecipeFav(int mIsRecipeFav) {
        this.mIsRecipeFav = mIsRecipeFav;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getRecipeName() {
        return mRecipeName;
    }

    public void setRecipeName(String mRecipeName) {
        this.mRecipeName = mRecipeName;
    }

    public String getRecipeIngredients() {
        return mRecipeIngredients;
    }

    public void setRecipeIngredients(String mRecipeIngredients) {
        this.mRecipeIngredients = mRecipeIngredients;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}
