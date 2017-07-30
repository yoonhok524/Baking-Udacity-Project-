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
            new FetchRecipes(mIdlingResource, mView).execute();
        } else {
            mView.onOccurredError(MainContract.ErrorType.NETWORK_DISCONNECT);
        }
    }

    boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork == null) ? false : true;
    }

}
