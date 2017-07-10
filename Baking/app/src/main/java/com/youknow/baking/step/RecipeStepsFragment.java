package com.youknow.baking.step;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youknow.baking.R;
import com.youknow.baking.data.Ingredient;
import com.youknow.baking.data.Recipe;
import com.youknow.baking.data.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeStepsFragment extends Fragment {

    private static Context mContext;

    @BindView(R.id.cv_ingredients) CardView cvIngredients;
    @BindView(R.id.rv_steps) RecyclerView rvSteps;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    private OnFragmentInteractionListener mListener;
    private Recipe mRecipe;

    public RecipeStepsFragment() {
        mListener = (OnFragmentInteractionListener) mContext;
    }

    public static RecipeStepsFragment newInstance(Context context, Recipe recipe) {
        mContext = context;
        RecipeStepsFragment fragment = new RecipeStepsFragment();
        Bundle args = new Bundle();
        args.putParcelable(mContext.getString(R.string.key_recipe), recipe);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mLayoutManager = new LinearLayoutManager(getContext());
        rvSteps.setLayoutManager(mLayoutManager);

        if (getArguments() != null) {
            mRecipe = getArguments().getParcelable(getString(R.string.key_recipe));
            mAdapter = new RecipeStepsAdapter(mRecipe.getSteps(), getContext(), mListener);
            rvSteps.setAdapter(mAdapter);

            cvIngredients.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onLoadedIngredients(mRecipe.getIngredients());
                }
            });
        }
    }

    public interface OnFragmentInteractionListener {
        void onLoadedIngredients(List<Ingredient> ingredients);
        void onLoadedStep(Step step);
    }
}
