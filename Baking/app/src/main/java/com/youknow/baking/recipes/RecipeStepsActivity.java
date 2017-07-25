package com.youknow.baking.recipes;

import com.youknow.baking.R;
import com.youknow.baking.data.Ingredient;
import com.youknow.baking.data.Recipe;
import com.youknow.baking.data.Step;
import com.youknow.baking.recipes.steps.details.StepDetailsFragment;
import com.youknow.baking.recipes.steps.ingredient.IngredientDetailsFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepsActivity extends AppCompatActivity implements StepListener {

    private static final int ID_INGREDIENT = -1;

    private Recipe mRecipe;
    private int mCurrentId = ID_INGREDIENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        mRecipe = getIntent().getParcelableExtra(getString(R.string.key_recipe));
        setTitle(mRecipe.getName());

        List<String> steps = new ArrayList<>();
        int size = mRecipe.getSteps().size();
        for (int i = 0; i < size; i++) {
            steps.add(getString(R.string.recipe_step_description, (i + 1)));
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, steps);

        if (savedInstanceState != null) {
            mRecipe = savedInstanceState.getParcelable(getString(R.string.key_step));
            mCurrentId = savedInstanceState.getInt(getString(R.string.key_current_step));
        }

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            ListFragment listFragment = RecipeStepsFragment.newInstance();
            listFragment.setListAdapter(adapter);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, listFragment)
                    .commit();

        } else {
            ListFragment stepDetailsFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.recipesteps_fragment);
            stepDetailsFragment.setListAdapter(adapter);
            stepDetailsFragment.setSelection(mCurrentId);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(getString(R.string.key_step), mRecipe);
        outState.putInt(getString(R.string.key_current_step), mCurrentId);
    }

    @Override
    public void onStepSelected(int id) {
        mCurrentId = id;

        if (mCurrentId == ID_INGREDIENT) {
            List<Ingredient> ingredients = mRecipe.getIngredients();
            Fragment fragment = IngredientDetailsFragment.newInstance(this, ingredients);
            if (findViewById(R.id.fragment_container) != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.details_fragment, fragment)
                        .commit();
            }
        } else {
            Step step = mRecipe.getSteps().get(mCurrentId);

            Bundle args = new Bundle();
            args.putParcelable(getString(R.string.key_step), step);
            args.putInt(getString(R.string.key_current_step), mCurrentId);
            args.putInt(getString(R.string.key_step_size), mRecipe.getSteps().size());

            StepDetailsFragment fragment = new StepDetailsFragment();
            fragment.setArguments(args);

            if (findViewById(R.id.fragment_container) != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.details_fragment, fragment)
                        .commit();
            }
        }
    }

}
