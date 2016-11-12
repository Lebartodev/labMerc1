package com.lebartodev.labmerc1.presenter;

/**
 * Created by Александр on 02.11.2016.
 */

public interface BaseListPresenter {
    void onRecreateView();
    void deleteItem(int position,Long id);
    void addItem(String name);
    void onStop();
    void initSubscription();

}
