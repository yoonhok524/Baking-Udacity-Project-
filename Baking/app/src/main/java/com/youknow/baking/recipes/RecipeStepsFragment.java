package com.youknow.baking.recipes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.youknow.baking.R;

public class RecipeStepsFragment extends ListFragment {

    private static final String TAG = RecipeStepsFragment.class.getSimpleName();
    private static RecipeStepsFragment INSTANCE = null;

    private StepListener mListener;

    public RecipeStepsFragment() {

    }

    public static RecipeStepsFragment newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RecipeStepsFragment();
        }

        return INSTANCE;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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


    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach");
    }
}
