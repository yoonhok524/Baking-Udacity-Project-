package com.youknow.baking.data;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

/**
 * Created by Aaron on 09/07/2017.
 */
@Data
public class Step implements Parcelable {
    String id;
    String shortDesc;
    String videoUrl;
    String thumbnailUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.shortDesc);
        dest.writeString(this.videoUrl);
        dest.writeString(this.thumbnailUrl);
    }

    protected Step(Parcel in) {
        this.id = in.readString();
        this.shortDesc = in.readString();
        this.videoUrl = in.readString();
        this.thumbnailUrl = in.readString();
    }

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}
