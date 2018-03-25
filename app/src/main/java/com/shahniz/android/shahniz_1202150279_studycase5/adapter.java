package com.shahniz.android.shahniz_1202150279_studycase5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shahniz on 25/03/2018.
 */

public class adapter extends RecyclerView.Adapter<adapter.holder>{
    //membuat variabel
    private Context context;
    private List<data> item;
    int id;

    //constructor nya
    public adapter(Context context, List<data> item, int id) {
        this.context = context;
        this.item = item;
        this.id = id;
    }

    //untuk menentukan viewholder untuk recycler nya
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview, parent, false);
        holder holder = new holder(view);
        return holder;
    }

    //untuk menentukan nilai objeknya sesuai dengan viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        data x = item.get(position);
        holder.td.setText(x.getTodo());
        holder.ds.setText(x.getDesc());
        holder.pr.setText(x.getPrior());
        holder.card.setCardBackgroundColor(context.getResources().getColor(this.id));
    }
    //mendapatkan item dari adapter
    public data getItem(int position){
        return item.get(position);
    }
    //menghapus item data pada todolist
    public void removeitem(int i){
        item.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, item.size());
    }
    //mendapatkan jumlah item
    @Override
    public int getItemCount() {
        return item.size();
    }

    //class holder yang terhubung dengan recycler view
    class holder extends RecyclerView.ViewHolder{
        TextView td, ds, pr;
        CardView card;
        public holder(View itemView) {
            //mengakses id pada layout dan cardview
            super(itemView);
            td = itemView.findViewById(R.id.todorv);
            ds = itemView.findViewById(R.id.descrv);
            pr = itemView.findViewById(R.id.priorv);
            card = itemView.findViewById(R.id.card);
        }
    }
}
