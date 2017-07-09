package com.youknow.baking;

import com.youknow.baking.data.Recipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rv_recipes) RecyclerView mRvRecipes;
    RecipesAdapter mRecipesAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);

        mRvRecipes.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 1);
        mRvRecipes.setLayoutManager(mLayoutManager);
        mRecipesAdapter = new RecipesAdapter(this);
        mRvRecipes.setAdapter(mRecipesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.fetchRecipes();
    }

    @Override
    public void onLoadedRecipes(List<Recipe> recipes) {
        mRecipesAdapter.update(recipes);
    }
}
