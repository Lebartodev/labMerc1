package com.lebartodev.labmerc1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lebartodev.labmerc1.model.Item;
import com.lebartodev.labmerc1.presenter.ListPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements ListPage {
    @ViewById
    RecyclerView itemsList;
    private ItemAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private ListPresenter presenter;
    @AfterViews
    void initPresenter(){
        presenter=new ListPresenter(this);
        presenter.initSubscription();

    }

    @Override
    public void addItem(Item item) {

        if(adapter==null) {
            List<Item> chats = new ArrayList<>();
            chats.add(item);
            adapter = new ItemAdapter(this, chats,presenter);
            mLayoutManager = new LinearLayoutManager(this);
            itemsList.setLayoutManager(mLayoutManager);
            itemsList.setAdapter(adapter);

        }
        else
        {
            adapter.addItem(item);
        }


    }

    @Override
    public void deleteItem(int position) {
        if(adapter!=null){
            adapter.deleteItem(position);
        }
    }
}
