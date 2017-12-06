package com.example.jigsaw.cookbook;

//This is the model class used to manage the data of the Recipe.
//It consists of getters and setters.

public class RecipeData {

    private int mId;
    private String mRecipeName;
    private String mRecipeIngredients;
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
