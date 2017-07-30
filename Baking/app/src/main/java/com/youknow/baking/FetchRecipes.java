package com.youknow.baking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.youknow.baking.IdlingResource.SimpleIdlingResource;
import com.youknow.baking.data.Recipe;
import com.youknow.baking.utils.HttpUtil;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Aaron on 30/07/2017.
 */

public class FetchRecipes extends AsyncTask<Void, Void, List<Recipe>> {

    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private SimpleIdlingResource mIdlingResource;
    private MainContract.View mView;

    public FetchRecipes(SimpleIdlingResource idlingResource, MainContract.View view) {
        mIdlingResource = idlingResource;
        mView = view;
    }

    @Override
    protected List<Recipe> doInBackground(Void... params) {
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }

        String rawData = HttpUtil.getResponseFromHttpUrl(BASE_URL);
        Gson gson = new GsonBuilder().create();

        List<Recipe> recipes = gson.fromJson(rawData, new TypeToken<List<Recipe>>(){}.getType());
        return recipes;
    }

    @Override
    protected void onPostExecute(List<Recipe> recipes) {
        super.onPostExecute(recipes);

        if (recipes == null) {
            mView.onOccurredError(MainContract.ErrorType.WRONG_DATA);
        } else {
            mView.onLoadedRecipes(recipes);
            if (mIdlingResource != null) {
                mIdlingResource.setIdleState(true);
            }
        }
    }
}