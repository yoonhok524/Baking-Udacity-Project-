package com.youknow.baking;

import com.google.gson.Gson;

import com.squareup.picasso.Picasso;
import com.youknow.baking.data.Recipe;
import com.youknow.baking.recipes.RecipeStepsActivity;
import com.youknow.baking.widget.RecipeWidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 09/07/2017.
 */

public class RecipesAdapter extends Adapter<RecipesAdapter.ViewHolder> {

    private Context mContext;
    private List<Recipe> mRecipes = new ArrayList<>();

    public RecipesAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.ViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);

        holder.mRecipe = recipe;
        holder.tvRecipeName.setText(recipe.getName());
        holder.tvRecipeIngredientNum.setText(mContext.getString(R.string.ingredient_num, recipe.getIngredients().size()));
        holder.tvRecipeStepNum.setText(mContext.getString(R.string.step_num, recipe.getSteps().size()));
        holder.tvServings.setText(mContext.getString(R.string.serving_num, recipe.getServings()));

        String imageUrl = recipe.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.with(mContext).load(imageUrl).into(holder.ivRecipe);
        }
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public void update(List<Recipe> recipes) {
        mRecipes.clear();
        mRecipes.addAll(recipes);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Recipe mRecipe;
        TextView tvRecipeName;
        ImageView ivRecipe;
        TextView tvRecipeIngredientNum;
        TextView tvRecipeStepNum;
        TextView tvServings;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRecipeName = (TextView) itemView.findViewById(R.id.tv_recipe_name);
            ivRecipe = (ImageView) itemView.findViewById(R.id.iv_recipe);
            tvRecipeIngredientNum = (TextView) itemView.findViewById(R.id.tv_recipe_ingredient_num);
            tvRecipeStepNum = (TextView) itemView.findViewById(R.id.tv_recipe_step_num);
            tvServings = (TextView) itemView.findViewById(R.id.tv_recipe_servings);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences pref = mContext.getSharedPreferences(mContext.getString(R.string.key_recipe), Context.MODE_PRIVATE);
                    String jsonRecipe = mRecipe == null ? null : new Gson().toJson(mRecipe);
                    pref.edit().putString(mContext.getString(R.string.key_recipe), jsonRecipe).apply();

                    AppWidgetManager widgetManager = AppWidgetManager.getInstance(mContext);
                    int[] ids = widgetManager.getAppWidgetIds(new ComponentName(mContext, RecipeWidget.class));
                    RecipeWidget.updateAppWidgets(mContext, widgetManager, ids, mRecipe);

                    Intent intent = new Intent(mContext, RecipeStepsActivity.class);
                    intent.putExtra(mContext.getString(R.string.key_recipe), mRecipe);
                    mContext.startActivity(intent);

                    Toast.makeText(mContext, mContext.getString(R.string.widget_updated), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
