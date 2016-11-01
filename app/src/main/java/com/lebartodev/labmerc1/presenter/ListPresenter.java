package com.lebartodev.labmerc1.presenter;

import com.lebartodev.labmerc1.ListPage;
import com.lebartodev.labmerc1.model.Item;
import com.lebartodev.labmerc1.model.ItemModel;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Александр on 01.11.2016.
 */

public class ListPresenter {
    private Subscription subscription;
    private ItemModel model;
    private ListPage listPage;



    public ListPresenter(ListPage listPage) {
        this.listPage = listPage;
        model=new ItemModel();
    }

    public void initSubscription(){
        subscription=model.getObservable().subscribe(new Action1<Item>() {
            @Override
            public void call(Item item) {
                listPage.addItem(item);


            }
        });
        model.startEmits();
    }
    public void deleteItem(int position){
        model.deleteItem(position);
        listPage.deleteItem(position);
    }
    private void onStop(){
        subscription.unsubscribe();
    }
}
