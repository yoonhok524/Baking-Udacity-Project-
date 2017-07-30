package com.youknow.baking;

import com.youknow.baking.data.Recipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.rv_recipes) RecyclerView mRvRecipes;
    @BindView(R.id.tv_network_disconnected) TextView mTvNetworkDisconnected;
    @BindView(R.id.tv_wrong_data) TextView mTvWrongData;
    RecipesAdapter mRecipesAdapter;

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);

        mRvRecipes.setHasFixedSize(true);
        mRecipesAdapter = new RecipesAdapter(this);
        mRvRecipes.setAdapter(mRecipesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mProgressBar.setVisibility(View.VISIBLE);
        mPresenter.fetchRecipes();
    }

    @Override
    public void onLoadedRecipes(List<Recipe> recipes) {
        mRecipesAdapter.update(recipes);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onOccurredError(MainContract.ErrorType type) {
        mProgressBar.setVisibility(View.GONE);
        switch (type) {
            case NETWORK_DISCONNECT:
                mTvNetworkDisconnected.setVisibility(View.VISIBLE);
                break;
            case WRONG_DATA:
                mTvWrongData.setVisibility(View.VISIBLE);
                break;
        }
    }

}
