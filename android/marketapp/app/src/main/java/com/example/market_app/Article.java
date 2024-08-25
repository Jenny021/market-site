package com.example.market_app;

import android.os.Parcelable;
import android.os.Parcel;

public class Article implements Parcelable {
    private String uuid;
    private String name;
    private double cost;

    public Article(String uuid, String name, double cost) {
        this.name = name;
        this.cost = cost;
        this.uuid = uuid;
    }

    protected Article(Parcel in) {
        uuid = in.readString();
        name = in.readString();
        cost = in.readDouble();
    }

    public Article() {
        this("", "", 0.0);
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(name);
        dest.writeDouble(cost);
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

