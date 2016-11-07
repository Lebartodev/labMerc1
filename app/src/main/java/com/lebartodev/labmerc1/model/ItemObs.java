package com.lebartodev.labmerc1.model;

import android.graphics.Color;

import com.lebartodev.labmerc1.utils.Consts;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Александр on 01.11.2016.
 */

public class ItemObs implements ItemModel {
    private List<Item> items;
    PublishSubject<Item>  subject = PublishSubject.create();
    PublishSubject<Boolean>  subjectCheck = PublishSubject.create();
    public ItemObs(){
        items = new ArrayList<>();
        for(int i =0;i<50;i++){
            Item item = new Item("Element "+(i+1),getColorByPosition(i));
            items.add(item);

        }
    }

    @Override
    public void startEmits(){
        for(Item item:items)
        subject.onNext(item);

    }

    private int getColorByPosition(int position) {
        int res = (position + 1) % 8;
        switch (res) {
            case 0:
                return Consts.COLOR_TRANS;
            case 1:
                return Consts.COLOR_RED;
            case 2:
                return Consts.COLOR_ORANGE;
            case 3:
                return Consts.COLOR_YELLOW;
            case 4:
                return Consts.COLOR_GREEN;
            case 5:
                return Consts.COLOR_BLUE;
            case 6:
                return Consts.COLOR_BLUE_DARK;
            case 7:
                return Consts.COLOR_PURPLE;
        }
        return Color.TRANSPARENT;


    }
    @Override
    public void deleteItem(int position){
        items.remove(position);
    }
    @Override
    public Observable<Item> getListObs(){
        return subject;

    }

    @Override
    public void addItem(String itemName,int color) {
        Item item = new Item(itemName,color);
    }

    @Override
    public void checkName(String name) {
        for(Item item:items){
            if(item.getTitle().equals(name)) {
                subjectCheck.onNext(false);
                return;
            }
        }
        subjectCheck.onNext(true);

    }

    @Override
    public Observable<Boolean> getCheckObs() {
        return subjectCheck;
    }


}
