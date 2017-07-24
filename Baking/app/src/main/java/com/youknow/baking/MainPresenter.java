package com.youknow.baking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.youknow.baking.data.Recipe;
import com.youknow.baking.data.Step;
import com.youknow.baking.utils.HttpUtil;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Created by Aaron on 09/07/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;
    }

    @Override
    public void fetchRecipes() {
        new FetchRecipes().execute();
    }

    private class FetchRecipes extends AsyncTask<Void, Void, List<Recipe>> {

        private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

        @Override
        protected List<Recipe> doInBackground(Void... params) {
            String rawData = HttpUtil.getResponseFromHttpUrl(BASE_URL);
            Gson gson = new GsonBuilder().create();

            List<Recipe> recipes = gson.fromJson(rawData, new TypeToken<List<Recipe>>(){}.getType());
            return recipes;
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            super.onPostExecute(recipes);
            mView.onLoadedRecipes(recipes);
        }
    }
}
