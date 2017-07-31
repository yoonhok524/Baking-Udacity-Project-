package com.youknow.baking.widget;

import com.google.gson.Gson;

import com.youknow.baking.IdlingResource.SimpleIdlingResource;
import com.youknow.baking.R;
import com.youknow.baking.data.Recipe;
import com.youknow.baking.recipes.RecipeStepsActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidget extends AppWidgetProvider {

    private static SimpleIdlingResource mIdlingResource;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        updateAppWidget(context, appWidgetManager, appWidgetId, getRecipe(context));
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Recipe recipe) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.recipes_widget);
        remoteViews.setTextViewText(R.id.widget_recipe_name, recipe.getName());

        remoteViews.setRemoteAdapter(R.id.widget_list_view, new Intent(context, RecipeWidgetService.class));

        Intent stepIntent = new Intent(context, RecipeStepsActivity.class);
        stepIntent.putExtra(context.getString(R.string.key_recipe), recipe);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, stepIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.widget_recipe_name, pendingIntent);
        remoteViews.setTextViewText(R.id.widget_recipe_name, recipe.getName());

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    public static void updateAppWidgets(Context context, AppWidgetManager appWidgetManager, int[] ids, Recipe recipe) {
        for (int appWidgetId : ids) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipe);
        }
    }

    public static Recipe getRecipe(Context context) {
        SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.key_recipe), Context.MODE_PRIVATE);
        String jsonRecipe = pref.getString(context.getString(R.string.key_recipe), null);
        return (jsonRecipe == null) ? null : new Gson().fromJson(jsonRecipe, Recipe.class);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

