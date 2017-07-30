package com.youknow.baking.recipes.steps;

import com.youknow.baking.R;
import com.youknow.baking.data.Ingredient;
import com.youknow.baking.data.Recipe;
import com.youknow.baking.data.Step;
import com.youknow.baking.recipes.StepListener;
import com.youknow.baking.recipes.steps.details.StepDetailsFragment;
import com.youknow.baking.recipes.steps.ingredient.IngredientDetailsFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.youknow.baking.data.Ingredient.ID_INGREDIENT;

public class DetailsActivity extends AppCompatActivity implements StepListener {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    List<Ingredient> mIngredients;
    int mCurrentId;
    Recipe mRecipe;
    private Fragment ingredientFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        if (savedInstanceState != null) {
            mIngredients = savedInstanceState.getParcelableArrayList(getString(R.string.key_ingredients));
            mCurrentId = savedInstanceState.getInt(getString(R.string.key_current_step));
            mRecipe = savedInstanceState.getParcelable(getString(R.string.key_recipe));

            if (mRecipe != null) {
                showDetailsStep();
            } else {
                if (ingredientFragment == null) {
                    ingredientFragment = IngredientDetailsFragment.newInstance(this, mIngredients);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.details_container, ingredientFragment)
                            .commit();
                }

            }
        } else {
            Intent intent = getIntent();
            if (intent.hasExtra(getString(R.string.key_ingredients))) {
                mIngredients = intent.getParcelableArrayListExtra(getString(R.string.key_ingredients));
                if (ingredientFragment == null) {
                    ingredientFragment = IngredientDetailsFragment.newInstance(this, mIngredients);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.details_container, ingredientFragment)
                            .commit();
                }
            } else {
                mCurrentId = intent.getIntExtra(getString(R.string.key_current_step), 0);
                mRecipe = intent.getParcelableExtra(getString(R.string.key_recipe));

                showDetailsStep();
            }
        }

    }

    @Override
    public void onStepSelected(int id) {
        mCurrentId = id;
        showDetailsStep();
    }

    private void showDetailsStep() {
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.key_step), mRecipe.getSteps().get(mCurrentId));
        args.putInt(getString(R.string.key_current_step), mCurrentId);
        args.putInt(getString(R.string.key_step_size), mRecipe.getSteps().size());

        Fragment fragment = new StepDetailsFragment();
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_container, fragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.key_ingredients), (ArrayList<? extends Parcelable>) mIngredients);
        outState.putInt(getString(R.string.key_current_step), mCurrentId);
        outState.putParcelable(getString(R.string.key_recipe), mRecipe);
    }
}
