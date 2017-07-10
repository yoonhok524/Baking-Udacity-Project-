package com.youknow.baking.step;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.youknow.baking.R;
import com.youknow.baking.data.Ingredient;
import com.youknow.baking.data.Recipe;
import com.youknow.baking.step.details.ingredient.IngredientDetailsFragment;

import java.util.List;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepsFragment.OnFragmentInteractionListener {

    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        mRecipe = getIntent().getParcelableExtra(getString(R.string.key_recipe));
        setTitle(mRecipe.getName());

        Fragment fragment = RecipeStepsFragment.newInstance(this, mRecipe);
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.key_recipe), mRecipe);
        fragment.setArguments(args);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onLoadedIngredients(List<Ingredient> ingredients) {
        Fragment fragment = IngredientDetailsFragment.newInstance(this, ingredients);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
