package com.youknow.baking.recipes.steps.ingredient;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
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

    @BindView(R.id.rv_ingredients) RecyclerView rvIngredients;
    IngredientAdapter mIngredientAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<Ingredient> mIngredients;

    public IngredientDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ingredient_details, container, false);
        ButterKnife.bind(this, root);
        getActivity().setTitle(getString(R.string.ingredients));
        return root;
    }

    public static Fragment newInstance(Context context, List<Ingredient> ingredients) {
        IngredientDetailsFragment fragment = new IngredientDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(context.getString(R.string.key_ingredients), (ArrayList<? extends Parcelable>) ingredients);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        mLayoutManager = new LinearLayoutManager(getContext());
        rvIngredients.setLayoutManager(mLayoutManager);

        if (getArguments() != null) {
            mIngredients = getArguments().getParcelableArrayList(getString(R.string.key_ingredients));

            mIngredientAdapter = new IngredientAdapter(mIngredients, getContext());
            rvIngredients.setAdapter(mIngredientAdapter);
        }
    }
}
