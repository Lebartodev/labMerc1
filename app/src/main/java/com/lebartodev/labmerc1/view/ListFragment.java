package com.lebartodev.labmerc1.view;

import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lebartodev.labmerc1.R;
import com.lebartodev.labmerc1.model.Item;
import com.lebartodev.labmerc1.presenter.ListPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_list)
public class ListFragment extends Fragment implements ListPage {

    @ViewById
    RecyclerView itemsList;
    private ItemAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private ListPresenter presenter;

    public ListFragment() {
    }

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
            adapter = new ItemAdapter(getActivity(), chats,presenter);
            mLayoutManager = new LinearLayoutManager(getActivity());
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
