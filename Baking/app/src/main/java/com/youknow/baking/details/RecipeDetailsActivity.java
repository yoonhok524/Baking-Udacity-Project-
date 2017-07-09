package com.youknow.baking.details;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.youknow.baking.R;
import com.youknow.baking.data.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {

    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        mRecipe = getIntent().getParcelableExtra(getString(R.string.key_recipe));
        setTitle(mRecipe.getName());

        Fragment fragment = RecipeDetailsFragment.newInstance(this, mRecipe);
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.key_recipe), mRecipe);
        fragment.setArguments(args);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_recipe_details, fragment).commit();
    }
}
