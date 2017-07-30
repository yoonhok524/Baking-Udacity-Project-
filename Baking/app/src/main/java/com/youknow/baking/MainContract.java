package com.youknow.baking;

import com.youknow.baking.IdlingResource.SimpleIdlingResource;
import com.youknow.baking.data.Recipe;

import java.util.List;

/**
 * Created by Aaron on 09/07/2017.
 */

public interface MainContract {
    public enum ErrorType {
        NETWORK_DISCONNECT,
        WRONG_DATA
    }

    interface View {

        void onLoadedRecipes(List<Recipe> recipes);

        void onOccurredError(ErrorType type);
    }

    interface Presenter {

        void fetchRecipes(SimpleIdlingResource idlingResource);
    }
}
