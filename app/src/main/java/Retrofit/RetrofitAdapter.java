package Retrofit;

import com.google.gson.GsonBuilder;

import Utility.StringDesirializer;
import Utility.UrlGenerator;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by jigsaw on 7/1/18.
 */

public class RetrofitAdapter {
    protected APIManager mApiManager;

    public APIManager getmApiManager() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(String.class, new StringDesirializer());
        if (mApiManager == null) {
            mApiManager = new RestAdapter.Builder()
                    .setEndpoint(UrlGenerator.BASE_URL)
                    .setConverter(new GsonConverter(gsonBuilder.create()))
                    .build()
                    .create(APIManager.class);
        }
        return mApiManager;
    }
}
