package com.lebartodev.labmerc1.model;

import android.graphics.Color;
import android.util.Log;

import com.lebartodev.labmerc1.utils.Consts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Александр on 01.11.2016.
 */

public class ItemObs implements ItemModel {

    public static PublishSubject<Item> subject = PublishSubject.create();
    PublishSubject<Boolean> subjectCheck = PublishSubject.create();

    public ItemObs() {
        if (!isDatabaseExist()) {
            for (int i = 0; i < 50; i++) {
                Item item = new Item("Element " + (i + 1), getColorByPosition(i));
                Item.save(item);
                //items.add(item);

            }
        }


    }

    private boolean isDatabaseExist() {
        Log.d("ItemObs", "Item count = " + Item.count(Item.class));
        if (Item.count(Item.class) > 0) {
            return true;
        } else return false;
    }

    @Override
    public void startEmits() {
        List<Item> items = Item.listAll(Item.class);
        for (Item item : items)
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
    public void deleteItem(String name) {
        List<Item> items = Item.find(Item.class, "title=?", name);
        items.get(0).delete();
    }

    @Override
    public void deleteItem(Long position) {
        Log.d("ItemObs","Position: "+position);
        Item item = Item.findById(Item.class, position);

        item.delete();
    }

    @Override
    public Observable<Item> getListObs() {
        return subject;

    }

    @Override
    public void addItem(String itemName, int color) {

        Item item = new Item(itemName, color);
        item.save();
        subject.onNext(item);
    }



    @Override
    public void checkName(String name) {

        List<Item> items = Item.find(Item.class, "title =?", name);
        if (items.size() > 0) {
            subjectCheck.onNext(false);
        } else
            subjectCheck.onNext(true);


    }

    @Override
    public Observable<Boolean> getCheckObs() {
        return subjectCheck;
    }


}
