package com.lebartodev.labmerc1.presenter;

import com.lebartodev.labmerc1.view.fragment.ListPage;
import com.lebartodev.labmerc1.model.Item;
import com.lebartodev.labmerc1.model.ItemObs;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Александр on 01.11.2016.
 */

public class ListPresenter implements BaseListPresenter {
    private Subscription subscription;
    private ItemObs model;
    private ListPage listPage;


    public ListPresenter(ListPage listPage) {
        this.listPage = listPage;
        model = new ItemObs();
    }

    @Override
    public void initSubscription() {
        subscription = model.getListObs().subscribe(new Action1<Item>() {
            @Override
            public void call(Item item) {
                listPage.addItem(item);
            }
        });

        model.startEmits();
    }
    @Override
    public void onRecreateView() {
        subscription = model.getListObs().subscribe(new Action1<Item>() {
            @Override
            public void call(Item item) {
                listPage.addItem(item);
            }
        });
        //model.startEmits();
    }


    public void deleteItem(int position,Long id) {
        model.deleteItem(id);
        listPage.deleteItem(position);
    }
    public void callDeleteItem(int position,Long id){
        listPage.callDelete(position,id);

    }

    @Override
    public void addItem(String name) {


    }

    @Override
    public void onStop() {
    //    subscription.unsubscribe();
    }
}
