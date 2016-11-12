package com.lebartodev.labmerc1.model;



import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by Александр on 01.11.2016.
 */
public class Item extends SugarRecord implements Parcelable {
    private String title;
    private int color = 0;


    public Item() {
    }

    protected Item(Parcel in) {
        title = in.readString();
        color = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getColor() {
        return color;
    }

    public Item(String title, int color) {
        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(color);
    }
}
