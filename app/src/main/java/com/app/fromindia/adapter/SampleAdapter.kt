package com.app.fromindia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.fromindia.R
import com.app.fromindia.model.DynamicMenu

class SampleAdapter(private val context: Context, private var personUtils: List<DynamicMenu>) : RecyclerView.Adapter<SampleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inflate_product_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tag = personUtils[position]
        holder.pName.setOnClickListener { updateList(personUtils) }
    }

    private fun updateList(aItem: List<DynamicMenu>) {
        personUtils = aItem
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return personUtils.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pName: ImageView
        var pJobProfile: TextView? = null

        init {
            pName = itemView.findViewById<View>(R.id.autoItemIM) as ImageView
            /*  pName = (TextView) itemView.findViewById(R.id.pNametxt);
            pJobProfile = (TextView) itemView.findViewById(R.id.pJobProfiletxt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    PersonUtils cpu = (PersonUtils) view.getTag();

                    Toast.makeText(view.getContext(), cpu.getPersonName() + " is " + cpu.getJobProfile(), Toast.LENGTH_SHORT).show();

                }
            });*/
        }
    }

}