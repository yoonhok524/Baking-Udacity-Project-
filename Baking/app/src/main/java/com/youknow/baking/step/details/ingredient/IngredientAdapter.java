package com.youknow.baking.step.details.ingredient;

import com.youknow.baking.R;
import com.youknow.baking.data.Ingredient;

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

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    List<Ingredient> mIngredients = new ArrayList<>();
    Context mContext;

    public IngredientAdapter(List<Ingredient> ingredients, Context context) {
        mIngredients.clear();
        mIngredients.addAll(ingredients);
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ingredient ingredient = mIngredients.get(position);
        holder.tvIngredientName.setText(ingredient.getIngredient());
        holder.tvIngredientQuantity.setText(String.valueOf(ingredient.getQuantity()));
        holder.tvIngredientMeasure.setText(ingredient.getMeasure());
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvIngredientName;
        TextView tvIngredientQuantity;
        TextView tvIngredientMeasure;

        public ViewHolder(View itemView) {
            super(itemView);
            tvIngredientName = (TextView) itemView.findViewById(R.id.tv_ingredient_name);
            tvIngredientQuantity = (TextView) itemView.findViewById(R.id.tv_ingredient_quantity);
            tvIngredientMeasure = (TextView) itemView.findViewById(R.id.tv_ingredient_measure);
        }
    }
}
