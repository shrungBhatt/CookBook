package com.example.jigsaw.cookbook;

/**
 * Created by jigsaw on 2/12/17.
 */

public class RecipeData {

    private int mId;
    private String mRecipeName;
    private String mRecipeIngredients;


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
}
