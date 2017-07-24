package com.youknow.baking.step;

import com.youknow.baking.data.Ingredient;
import com.youknow.baking.data.Step;

import java.util.List;

/**
 * Created by Aaron on 24/07/2017.
 */

public interface RecipeListener {
    void onLoadedIngredients(List<Ingredient> ingredients);
    void onLoadedStep(int currentStep, int size, boolean addToBackStack);
}
