package com.lebartodev.labmerc1.view;

import android.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lebartodev.labmerc1.R;
import com.lebartodev.labmerc1.model.Item;
import com.lebartodev.labmerc1.presenter.ListPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById
    FloatingActionButton fab;

    private void showAddFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container, AddFragment_.builder().build()).addToBackStack("tag").commit();
    }


    @AfterViews
    void initList() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddFragment();
            }
        });
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container, ListFragment_.builder().build()).commit();

    }

}
