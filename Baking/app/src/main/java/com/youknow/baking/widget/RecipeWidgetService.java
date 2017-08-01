package com.youknow.baking.widget;

import com.google.gson.Gson;

import com.youknow.baking.R;
import com.youknow.baking.data.Ingredient;
import com.youknow.baking.data.Recipe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 31/07/2017.
 */

public class RecipeWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeRemoteViewsFactory(this.getApplicationContext(), intent);
    }

}

class RecipeRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;
    Recipe mRecipe;
    List<Ingredient> mIngredients = new ArrayList<>();

    public RecipeRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
    }

    public static Recipe getRecipe(Context context) {
        SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.key_recipe), Context.MODE_PRIVATE);
        String jsonRecipe = pref.getString(context.getString(R.string.key_recipe), null);
        return (jsonRecipe == null) ? null : new Gson().fromJson(jsonRecipe, Recipe.class);
    }

    @Override
    public void onCreate() {
        mRecipe = getRecipe(mContext);
        mIngredients.clear();
        if (mRecipe != null) {
            mIngredients.addAll(mRecipe.getIngredients());
        }
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Ingredient ingredient = mIngredients.get(position);

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.recipes_widget_item);
        rv.setTextViewText(R.id.widget_ingredient_name, ingredient.getIngredient());
        rv.setTextViewText(R.id.widget_ingredient_quantity, String.valueOf(ingredient.getQuantity()));
        rv.setTextViewText(R.id.widget_ingredient_measure, ingredient.getMeasure());

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}