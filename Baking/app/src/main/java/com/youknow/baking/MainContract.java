package com.youknow.baking;

import com.youknow.baking.data.Recipe;

import java.util.List;

/**
 * Created by Aaron on 09/07/2017.
 */

public interface MainContract {
    interface View {

        void onLoadedRecipes(List<Recipe> recipes);

        void onDisconnectedNetwork();
    }

    interface Presenter {

        void fetchRecipes();
    }
}
