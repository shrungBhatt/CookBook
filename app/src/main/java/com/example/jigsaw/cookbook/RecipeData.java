package com.example.jigsaw.cookbook;

//This is the model class used to manage the data of the Recipe.
//It consists of getters and setters.

import com.google.gson.annotations.SerializedName;

import Model.BaseModel;

public class RecipeData extends BaseModel{

    @SerializedName("id")
    private int mId;
    @SerializedName("recipe")
    private String mRecipeName;
    @SerializedName("ingredients")
    private String mRecipeIngredients;
    @SerializedName("image_url")
    private String mImageUrl;


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
