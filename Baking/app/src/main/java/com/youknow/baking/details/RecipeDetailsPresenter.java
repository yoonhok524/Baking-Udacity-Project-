package com.youknow.baking.details;

/**
 * Created by Aaron on 10/07/2017.
 */

public class RecipeDetailsPresenter implements RecipeDetailsContract.Presenter {

    RecipeDetailsContract.View mView;

    public RecipeDetailsPresenter(RecipeDetailsContract.View view) {
        mView = view;
    }
}
