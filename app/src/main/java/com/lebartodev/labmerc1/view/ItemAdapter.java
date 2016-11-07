package com.lebartodev.labmerc1.view;

/**
 * Created by Александр on 01.11.2016.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lebartodev.labmerc1.utils.Consts;
import com.lebartodev.labmerc1.R;
import com.lebartodev.labmerc1.model.Item;
import com.lebartodev.labmerc1.presenter.ListPresenter;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemVH> {
    private List<Item> items;
    private Context context;
    private ListPresenter presenter;

    public ItemAdapter(Context context, List<Item> items, ListPresenter presenter) {
        this.context = context;
        this.items = items;
        this.presenter=presenter;


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

        ((GradientDrawable)holder.icon.getBackground()).setColor(getColor(items.get(position).getColor()));



        
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new MaterialDialog.Builder(context)
                        .title(R.string.you_sure)
                        .content(R.string.you_sure_content)
                        .positiveText(R.string.agree)
                        .negativeText(R.string.disagree)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                presenter.deleteItem(holder.getAdapterPosition());
                            }
                        })
                        .show();

                return false;
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
                return Color.argb(255,105,182,220);
            case Consts.COLOR_BLUE_DARK:
                return Color.BLUE;
            case Consts.COLOR_GREEN:
                return Color.GREEN;
            case Consts.COLOR_ORANGE:
                return Color.argb(255,255,183,77);
            case Consts.COLOR_PURPLE:
                return Color.argb(255,224,64,251);
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
        private View view;

        public ItemVH(View itemView) {


            super(itemView);
            this.view = itemView;

            title = (TextView) itemView.findViewById(R.id.listTitle);

            icon = (ImageView) itemView.findViewById(R.id.itemImage);


        }
    }
}
