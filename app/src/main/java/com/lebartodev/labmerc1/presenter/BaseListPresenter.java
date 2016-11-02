package com.lebartodev.labmerc1.presenter;

/**
 * Created by Александр on 02.11.2016.
 */

public interface BaseListPresenter {
    void deleteItem(int position);
    void addItem(String name);
    void onStop();
    void initSubscription();

}
