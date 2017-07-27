package com.youknow.baking.data;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

/**
 * Created by Aaron on 09/07/2017.
 */
@Data
public class Ingredient implements Parcelable {

    public static final int ID_INGREDIENT = -1;

    float quantity;
    String measure;
    String ingredient;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.quantity);
        dest.writeString(this.measure);
        dest.writeString(this.ingredient);
    }

    protected Ingredient(Parcel in) {
        this.quantity = in.readFloat();
        this.measure = in.readString();
        this.ingredient = in.readString();
    }

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
