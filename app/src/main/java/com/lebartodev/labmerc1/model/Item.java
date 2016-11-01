package com.lebartodev.labmerc1.model;

/**
 * Created by Александр on 01.11.2016.
 */

public class Item {
   private  String title;
    private boolean selected=false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Item(String title) {
        this.title = title;
    }
}
