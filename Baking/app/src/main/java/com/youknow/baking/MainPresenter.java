package com.youknow.baking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.youknow.baking.IdlingResource.SimpleIdlingResource;
import com.youknow.baking.data.Recipe;
import com.youknow.baking.utils.HttpUtil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Aaron on 09/07/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();
    MainContract.View mView;
    Context mContext;
    SimpleIdlingResource mIdlingResource;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mContext = (Context) view;
    }

    @Override
    public void fetchRecipes(SimpleIdlingResource idlingResource) {
        mIdlingResource = idlingResource;

        if (isNetworkConnected(mContext)) {
            new FetchRecipes().execute();
        } else {
            mView.onOccurredError(MainContract.ErrorType.NETWORK_DISCONNECT);
        }
    }

    boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork == null) ? false : true;
    }

    private class FetchRecipes extends AsyncTask<Void, Void, List<Recipe>> {

        private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

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
}
