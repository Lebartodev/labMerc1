package com.lebartodev.labmerc1;

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
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lebartodev.labmerc1.model.Item;
import com.lebartodev.labmerc1.model.ItemModel;
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


        holder.icon.setBackgroundColor(getColorByPosition(position));
        if (items.get(position).isSelected()) {
            holder.view.setBackgroundColor(Color.LTGRAY);
            holder.title.setTextColor(Color.WHITE);
        } else {
            holder.view.setBackgroundColor(Color.WHITE);
            holder.title.setTextColor(Color.BLACK);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.get(holder.getAdapterPosition()).setSelected(true);
                notifyDataSetChanged();

                Toast.makeText(context, "You click on element " + (holder.getAdapterPosition() + 1), Toast.LENGTH_SHORT).show();


            }

        });
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

    private int getColorByPosition(int position) {
        int res = (position + 1) % 8;
        switch (res) {
            case 0:
                return Color.RED;
            case 1:
                return Color.MAGENTA;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.BLUE;
            case 5:
                return Color.BLUE;
            case 6:
                return Color.CYAN;
            case 7:
                return Color.TRANSPARENT;
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
