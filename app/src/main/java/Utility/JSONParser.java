package Utility;

import android.util.Log;

import com.example.jigsaw.cookbook.RecipeData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.FeedBackData;

/**
 * Created by jigsaw on 14/1/18.
 */

public class JSONParser {

    private static final String TAG = "JSONParser";

    public static List<RecipeData> getRecipeDatas(String result) {

        List<RecipeData> recipeDatas = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(result);

            //Loop used for storing the data in the ArrayList, by parsing the response string.
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                RecipeData recipeData = new RecipeData();

                recipeData.setId(Integer.valueOf(jsonObject.getString("id")));
                recipeData.setRecipeName(jsonObject.getString("recipe"));
                recipeData.setRecipeIngredients(jsonObject.getString("ingredient"));
                recipeData.setmImageUrl(jsonObject.getString("image_url"));
                recipeData.setmIsRecipeFav(Integer.valueOf(jsonObject.getString("favourite")));
                recipeData.setmRecipeCategory(jsonObject.getString("category"));
                recipeData.setmRecipeCuisine(jsonObject.getString("cuisine"));
                recipeData.setmRecipeOverview(jsonObject.getString("overview"));
                recipeData.setmRecipeRating(Float.valueOf(jsonObject.getString("rating")));

                recipeDatas.add(recipeData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeDatas;
    }


    public static List<FeedBackData> getFeedBackDatas(String result) {

        List<FeedBackData> feedBackDatas = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(result);

            //Loop used for storing the data in the ArrayList, by parsing the response string.
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                FeedBackData feedBackData = new FeedBackData();

                feedBackData.setmRecipeName(jsonObject.getString("recipe_name"));
                feedBackData.setmId(jsonObject.getString("id"));
                feedBackData.setmMacId(jsonObject.getString("mac_id"));
                feedBackData.setmAuthor(jsonObject.getString("username"));
                feedBackData.setmReview(jsonObject.getString("review"));
                feedBackData.setmRating(jsonObject.getString("rating"));
                feedBackData.setmDate(jsonObject.getString("date"));

                feedBackDatas.add(feedBackData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return feedBackDatas;
    }

}
