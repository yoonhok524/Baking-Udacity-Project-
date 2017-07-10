package com.youknow.baking.step;

import com.youknow.baking.R;
import com.youknow.baking.data.Step;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 10/07/2017.
 */

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.ViewHolder> {

    private List<Step> mSteps = new ArrayList<>();
    private Context mContext;
    private RecipeStepsFragment.OnFragmentInteractionListener mListener;

    public RecipeStepsAdapter(List<Step> steps, Context context, RecipeStepsFragment.OnFragmentInteractionListener listener) {
        mContext = context;
        mListener = listener;
        mSteps.clear();
        mSteps.addAll(steps);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_step, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mStep = mSteps.get(position);
        holder.mTvStep.setText(mContext.getString(R.string.recipe_step_description, (position+1)));
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Step mStep;
        TextView mTvStep;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvStep = (TextView) itemView.findViewById(R.id.tv_step);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onLoadedStep(mStep);
                }
            });
        }
    }
}
