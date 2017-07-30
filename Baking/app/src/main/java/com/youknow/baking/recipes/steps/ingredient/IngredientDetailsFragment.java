package com.youknow.baking.recipes.steps.ingredient;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youknow.baking.R;
import com.youknow.baking.data.Ingredient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientDetailsFragment extends Fragment {

    private static final String TAG = "IngredientDetailsFragme";

    private static IngredientDetailsFragment INSTANCE;

    @BindView(R.id.rv_ingredients) RecyclerView rvIngredients;
    IngredientAdapter mIngredientAdapter;
    List<Ingredient> mIngredients;
    private Parcelable mLayoutManagerSavedState;

    public IngredientDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ingredient_details, container, false);
        ButterKnife.bind(this, root);
        getActivity().setTitle(getString(R.string.ingredients));

        return root;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mLayoutManagerSavedState = savedInstanceState.getParcelable(getString(R.string.key_ingredient_position));
        }
    }

    public static Fragment newInstance(Context context, List<Ingredient> ingredients) {
        if (INSTANCE == null) {
            INSTANCE = new IngredientDetailsFragment();
        }

        Bundle args = new Bundle();
        args.putParcelableArrayList(context.getString(R.string.key_ingredients), (ArrayList<? extends Parcelable>) ingredients);
        INSTANCE.setArguments(args);
        return INSTANCE;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments() != null) {
            mIngredients = getArguments().getParcelableArrayList(getString(R.string.key_ingredients));

            mIngredientAdapter = new IngredientAdapter(mIngredients, getContext());
            rvIngredients.setAdapter(mIngredientAdapter);

            if (mLayoutManagerSavedState != null) {
                rvIngredients.getLayoutManager().onRestoreInstanceState(mLayoutManagerSavedState);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getString(R.string.key_ingredient_position), rvIngredients.getLayoutManager().onSaveInstanceState());
    }
}
