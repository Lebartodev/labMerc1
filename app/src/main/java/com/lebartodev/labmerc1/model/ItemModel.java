package com.lebartodev.labmerc1.model;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Александр on 01.11.2016.
 */

public class ItemModel {
    private List<Item> items;
    PublishSubject<Item>  subject = PublishSubject.create();

    public void startEmits(){
        items = new ArrayList<>();
        for(int i =0;i<50;i++){
            Item item = new Item("Element "+(i+1));
            items.add(item);
            subject.onNext(item);
        }

    }
    public void deleteItem(int position){
        items.remove(position);
    }
    public Observable<Item> getObservable(){
        return subject;

    }




}
