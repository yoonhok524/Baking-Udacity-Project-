package com.youknow.baking.recipes;

import com.youknow.baking.R;
import com.youknow.baking.data.Ingredient;
import com.youknow.baking.data.Recipe;
import com.youknow.baking.data.Step;
import com.youknow.baking.recipes.steps.DetailsActivity;
import com.youknow.baking.recipes.steps.details.StepDetailsFragment;
import com.youknow.baking.recipes.steps.ingredient.IngredientDetailsFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.youknow.baking.data.Ingredient.ID_INGREDIENT;

public class RecipeStepsActivity extends AppCompatActivity implements StepListener {

    private static final String TAG = RecipeStepsActivity.class.getSimpleName();

    private Recipe mRecipe;
    private int mCurrentId = ID_INGREDIENT;
    private boolean isLargeScreen = false;

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
            mCurrentId = savedInstanceState.getInt(getString(R.string.key_current_step));
        }

        if (findViewById(R.id.fragment_container) != null) {
            isLargeScreen = false;
            ListFragment listFragment = RecipeStepsFragment.newInstance();
            listFragment.setListAdapter(adapter);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, listFragment)
                    .commit();
        } else {
            isLargeScreen = true;
            ListFragment stepDetailsFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.recipesteps_fragment);
            stepDetailsFragment.setListAdapter(adapter);
            stepDetailsFragment.setSelection(mCurrentId);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(getString(R.string.key_current_step), mCurrentId);
    }

    @Override
    public void onStepSelected(int id) {
        mCurrentId = id;

        if (isLargeScreen) {
            if (mCurrentId == ID_INGREDIENT) {
                List<Ingredient> ingredients = mRecipe.getIngredients();
                Fragment fragment = IngredientDetailsFragment.newInstance(this, ingredients);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.details_fragment, fragment)
                        .commit();
            } else {
                Step step = mRecipe.getSteps().get(mCurrentId);

                Bundle args = new Bundle();
                args.putParcelable(getString(R.string.key_step), step);
                args.putInt(getString(R.string.key_current_step), mCurrentId);
                args.putInt(getString(R.string.key_step_size), mRecipe.getSteps().size());

                StepDetailsFragment fragment = new StepDetailsFragment();
                fragment.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.details_fragment, fragment)
                        .commit();
            }
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            if (mCurrentId == ID_INGREDIENT) {
                intent.putParcelableArrayListExtra(getString(R.string.key_ingredients), (ArrayList<? extends Parcelable>) mRecipe.getIngredients());
            } else {
                intent.putExtra(getString(R.string.key_recipe), mRecipe);
                intent.putExtra(getString(R.string.key_current_step), mCurrentId);
            }

            startActivity(intent);
        }
    }

}
