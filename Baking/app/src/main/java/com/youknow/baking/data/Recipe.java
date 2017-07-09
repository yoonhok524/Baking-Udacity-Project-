package com.youknow.baking.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created by Aaron on 09/07/2017.
 */
@Data
public class Recipe implements Parcelable {
    String id;
    String name;
    List<Ingredient> ingredients;
    List<Step> steps;
    int servings;
    String imageUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeList(this.ingredients);
        dest.writeList(this.steps);
        dest.writeInt(this.servings);
        dest.writeString(this.imageUrl);
    }

    protected Recipe(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.ingredients = new ArrayList<Ingredient>();
        in.readList(this.ingredients, Ingredient.class.getClassLoader());
        this.steps = new ArrayList<Step>();
        in.readList(this.steps, Step.class.getClassLoader());
        this.servings = in.readInt();
        this.imageUrl = in.readString();
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
