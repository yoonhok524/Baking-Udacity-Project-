package com.youknow.baking.details;

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
import com.youknow.baking.data.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsFragment extends Fragment implements RecipeDetailsContract.View {

    private static Context mContext;

    @BindView(R.id.cv_ingredients) CardView cvIngredients;
    @BindView(R.id.rv_steps) RecyclerView rvSteps;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    private OnFragmentInteractionListener mListener;
    private RecipeDetailsContract.Presenter mPresenter;
    private Recipe mRecipe;

    public RecipeDetailsFragment() {
        mPresenter = new RecipeDetailsPresenter(this);
    }

    public static RecipeDetailsFragment newInstance(Context context, Recipe recipe) {
        mContext = context;
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(mContext.getString(R.string.key_recipe), recipe);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe_details, container, false);
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

            mAdapter = new RecipeStepsAdapter(mRecipe.getSteps(), getContext());
            rvSteps.setAdapter(mAdapter);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
