package com.lebartodev.labmerc1.view.adapter;

/**
 * Created by Александр on 01.11.2016.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lebartodev.labmerc1.utils.Consts;
import com.lebartodev.labmerc1.R;
import com.lebartodev.labmerc1.model.Item;
import com.lebartodev.labmerc1.presenter.ListPresenter;
import com.tubb.smrv.SwipeHorizontalMenuLayout;
import com.tubb.smrv.SwipeMenuLayout;
import com.tubb.smrv.listener.SwipeSwitchListener;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemVH> {
    private List<Item> items;
    private Context context;
    private ListPresenter presenter;

    public ItemAdapter(Context context, List<Item> items, ListPresenter presenter) {
        this.context = context;
        this.items = items;
        this.presenter = presenter;


    }

    public List<Item> getItems() {
        return items;

    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
        notifyDataSetChanged();

    }

    public void deleteItem(int position) {
        items.remove(position);
        notifyDataSetChanged();


    }

    @Override
    public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemVH(v);
    }


    @Override
    public void onBindViewHolder(final ItemVH holder, int position) {


        holder.title.setText(items.get(position).getTitle());

        ((GradientDrawable) holder.icon.getBackground()).setColor(getColor(items.get(position).getColor()));
        holder.swipeLayout.computeScroll();
        holder.deleteAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.callDeleteItem(holder.getAdapterPosition(),items.get(holder.getAdapterPosition()).getId());
                holder.swipeLayout.smoothCloseMenu(400);
            }
        });


    }

    private int getColor(int color) {

        switch (color) {
            case Consts.COLOR_TRANS:
                return Color.TRANSPARENT;
            case Consts.COLOR_RED:
                return Color.RED;
            case Consts.COLOR_BLUE:
                return Color.argb(255, 105, 182, 220);
            case Consts.COLOR_BLUE_DARK:
                return Color.BLUE;
            case Consts.COLOR_GREEN:
                return Color.GREEN;
            case Consts.COLOR_ORANGE:
                return Color.argb(255, 255, 183, 77);
            case Consts.COLOR_PURPLE:
                return Color.argb(255, 224, 64, 251);
            case Consts.COLOR_YELLOW:
                return Color.YELLOW;
        }
        return Color.TRANSPARENT;


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemVH extends RecyclerView.ViewHolder {
        private TextView title;

        private ImageView icon;
        private SwipeHorizontalMenuLayout swipeLayout;
        private ImageView deleteAction;
        private View view;

        public ItemVH(View itemView) {


            super(itemView);
            this.view = itemView;
            swipeLayout = (SwipeHorizontalMenuLayout) itemView.findViewById(R.id.swipeLayout);

            deleteAction= (ImageView) itemView.findViewById(R.id.deleteAction);



            title = (TextView) itemView.findViewById(R.id.listTitle);

            icon = (ImageView) itemView.findViewById(R.id.itemImage);


        }
    }
}
