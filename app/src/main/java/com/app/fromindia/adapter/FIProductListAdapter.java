package com.app.fromindia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.fromindia.R;
import com.app.fromindia.model.MenuItem;
import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FIProductListAdapter extends LoopingPagerAdapter<MenuItem> {

    private static final int VIEW_TYPE_NORMAL = 100;
    private static final int VIEW_TYPE_SPECIAL = 101;

    public FIProductListAdapter(Context context, ArrayList<MenuItem> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected int getItemViewType(int listPosition) {
        //if (itemList.get(listPosition) == 0) return VIEW_TYPE_SPECIAL;
        return VIEW_TYPE_NORMAL;
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {

        return LayoutInflater.from(context).inflate(R.layout.inflate_product_item, container, false);
    }

    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
        if (viewType == VIEW_TYPE_SPECIAL) return;
        //  convertView.findViewById(R.id.image).setBackgroundColor(context.getResources().getColor(getBackgroundColor(listPosition)));
        // TextView description = convertView.findViewById(R.id.description);
        //description.setText(String.valueOf(itemList.get(listPosition)));
        ImageView aAutoScrollIM = convertView.findViewById(R.id.autoItemIM);
        Glide.with(context).load(itemList.get(listPosition).getMenuUrl()).into(aAutoScrollIM);

    }


    private int getBackgroundColor(int number) {
        switch (number) {
            case 0:
                return android.R.color.holo_red_light;
            case 1:
                return android.R.color.holo_orange_light;
            case 2:
                return android.R.color.holo_green_light;
            case 3:
                return android.R.color.holo_blue_light;
            case 4:
                return android.R.color.holo_purple;
            case 5:
                return android.R.color.black;
            default:
                return android.R.color.black;
        }
    }

    private void test() {
    }
}
