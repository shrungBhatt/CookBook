package Utility;

import com.example.jigsaw.cookbook.RecipeData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jigsaw on 14/1/18.
 */

public class JSONParser {


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

                recipeDatas.add(recipeData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeDatas;
    }


}
