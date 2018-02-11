package Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jigsaw on 11/2/18.
 */

public class RecipeDataRealm extends RealmObject {

    @PrimaryKey
    private int id;
    private String recipeName;
    private String recipeIngredients;
    private String imageUrl;
    private String recipeCategory;
    private String recipeCuisine;
    private int recipeFav;
    private String recipeOverview;
    private float recipeRating;


    public String getRecipeOverview() {
        return recipeOverview;
    }

    public void setRecipeOverview(String recipeOverview) {
        this.recipeOverview = recipeOverview;
    }

    public float getRecipeRating() {
        return recipeRating;
    }

    public void setRecipeRating(float recipeRating) {
        this.recipeRating = recipeRating;
    }


    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public String getRecipeCuisine() {
        return recipeCuisine;
    }

    public void setRecipeCuisine(String recipeCuisine) {
        this.recipeCuisine = recipeCuisine;
    }

    public int getRecipeFav() {
        return recipeFav;
    }

    public void setRecipeFav(int recipeFav) {
        this.recipeFav = recipeFav;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
