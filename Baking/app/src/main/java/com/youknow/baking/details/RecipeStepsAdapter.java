package com.youknow.baking.details;

import com.youknow.baking.R;
import com.youknow.baking.data.Step;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    public RecipeStepsAdapter(List<Step> steps, Context context) {
        mContext = context;
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
        Step step = mSteps.get(position);
        holder.tvStep.setText(mContext.getString(R.string.recipe_step_description, (position+1)));
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvStep;

        public ViewHolder(View itemView) {
            super(itemView);
            tvStep = (TextView) itemView.findViewById(R.id.tv_step);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Implement!
                }
            });
        }
    }
}
