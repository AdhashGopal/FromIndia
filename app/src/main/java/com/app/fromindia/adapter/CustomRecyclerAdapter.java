package com.app.fromindia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.fromindia.R;
import com.app.fromindia.model.DynamicMenu;

import java.util.ArrayList;
import java.util.List;


public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<DynamicMenu> personUtils;
    private AdapterCallback mListener;

    public interface AdapterCallback {
        void onMethodCallback();
    }

    public CustomRecyclerAdapter(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_product_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(personUtils.get(position));
        DynamicMenu pu = personUtils.get(position);
        holder.pName.setText(pu.getMenuImage());
        holder.pJobProfile.setText(pu.getMenuName());
        holder.aCheck.setChecked(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMethodCallback();
            }
        });
    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    CustomRecyclerAdapter(AdapterCallback listener) {
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView pName;
        public TextView pJobProfile;
        public CheckBox aCheck;

        public ViewHolder(View itemView) {
            super(itemView);

            /// pName = (TextView) itemView.findViewById(R.id.pNametxt);
            // pJobProfile = (TextView) itemView.findViewById(R.id.pJobProfiletxt);

          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    PersonUtils cpu = (PersonUtils) view.getTag();

                    Toast.makeText(view.getContext(), cpu.getPersonName() + " is " + cpu.getJobProfile(), Toast.LENGTH_SHORT).show();

                }
            });*/

        }
    }

}