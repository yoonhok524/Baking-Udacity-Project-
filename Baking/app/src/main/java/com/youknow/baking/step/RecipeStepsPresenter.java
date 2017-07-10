package com.youknow.baking.step;

/**
 * Created by Aaron on 10/07/2017.
 */

public class RecipeStepsPresenter implements RecipeStepsContract.Presenter {

    RecipeStepsContract.View mView;

    public RecipeStepsPresenter(RecipeStepsContract.View view) {
        mView = view;
    }
}
