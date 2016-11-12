package com.lebartodev.labmerc1.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lebartodev.labmerc1.R;
import com.lebartodev.labmerc1.model.Item;
import com.lebartodev.labmerc1.presenter.ListPresenter;
import com.lebartodev.labmerc1.view.adapter.ItemAdapter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_list)
public class ListFragment extends Fragment implements ListPage {
    private String LOG_TAG = "ListFragment";

    @ViewById
    RecyclerView itemsList;
    private ItemAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ListPresenter presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        Log.d(LOG_TAG, "onActivityCreated");
        mLayoutManager = new LinearLayoutManager(getActivity());
        itemsList.setLayoutManager(mLayoutManager);
        itemsList.setAdapter(adapter);
        if (presenter == null) {


            presenter = new ListPresenter(this);
            List<Item> chats = new ArrayList<>();
            adapter = new ItemAdapter(getActivity(), chats, presenter);
            itemsList.setAdapter(adapter);



            presenter.initSubscription();


        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
    }

    public ListFragment() {


    }


    @Override
    public void onResume() {
        super.onResume();

        Log.d("ListFragment", "onResume");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getActivity().findViewById(R.id.fab).setVisibility(View.VISIBLE);
    }


    @Override
    public void addItem(Item item) {
        Log.d(LOG_TAG, "addItem");
        adapter.addItem(item);


    }

    @Override
    public void deleteItem(int position) {
        if (adapter != null) {
            adapter.deleteItem(position);
        }
    }

    @Override
    public void callDelete(final int position, final Long id) {
        new MaterialDialog.Builder(getActivity())
                .title("Delete?")
                .content("Are you sure to delete")
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        presenter.deleteItem(position, id);
                    }
                })
                .show();
    }
}
