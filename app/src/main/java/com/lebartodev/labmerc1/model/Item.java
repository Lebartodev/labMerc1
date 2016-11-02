package com.lebartodev.labmerc1.model;

/**
 * Created by Александр on 01.11.2016.
 */

public class Item {
   private  String title;
    private int color=0;

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


}
