package com.youknow.baking.recipes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.youknow.baking.R;
import com.youknow.baking.data.Recipe;

public class RecipeStepsFragment extends ListFragment {

    private static final String TAG = RecipeStepsFragment.class.getSimpleName();
    private StepListener mListener;

    public RecipeStepsFragment() {

    }

    public static RecipeStepsFragment newInstance() {
        return new RecipeStepsFragment();
    }

    @Override
    public void onStart() {
        super.onStart();

        View tvHeader = View.inflate(getContext(), R.layout.header_steps, null);
        getListView().addHeaderView(tvHeader);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (StepListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement StepListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        setSelection((int) id);
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);
        mListener.onStepSelected(position);
    }
}
