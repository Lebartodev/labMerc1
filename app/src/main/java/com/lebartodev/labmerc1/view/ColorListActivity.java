package com.lebartodev.labmerc1.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.lebartodev.labmerc1.R;
import com.lebartodev.labmerc1.utils.Consts;
import com.lebartodev.labmerc1.view.fragment.*;
import com.lebartodev.labmerc1.view.fragment.ListFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.a_color_list)
public class ColorListActivity extends AppCompatActivity {

    private final String LOG_TAG = "ColorListActivity";

    @ViewById
    FloatingActionButton fab;
    private FragmentManager fragmentManager;

    private void showAddFragment() {
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_add, com.lebartodev.labmerc1.view.fragment.AddFragment_.builder().build(), Consts.FRAGMENT_ADD)
                .commit();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
        fragmentManager = getFragmentManager();
        Fragment listFragment = fragmentManager.findFragmentByTag(Consts.FRAGMENT_LIST);
        if (listFragment == null) {
            listFragment = ListFragment_.builder().build();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, listFragment, Consts.FRAGMENT_LIST)
                    .commit();
        }


    }

    @AfterViews
    void initList() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddFragment();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

}
