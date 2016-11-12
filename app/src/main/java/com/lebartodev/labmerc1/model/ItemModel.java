package com.lebartodev.labmerc1.model;

import rx.Observable;

/**
 * Created by Александр on 02.11.2016.
 */

public interface ItemModel {
    void startEmits();
    void deleteItem(String title);
    void deleteItem(Long position);
    Observable<Item> getListObs();
    void addItem(String name,int color);

    
    void checkName(String name);
    Observable<Boolean> getCheckObs();



}
