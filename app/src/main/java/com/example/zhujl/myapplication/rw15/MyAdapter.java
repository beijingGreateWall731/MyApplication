package com.example.zhujl.myapplication.rw15;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhujl.myapplication.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<Fruit> list;
    public MyAdapter(Context context,List<Fruit> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.rv_item,viewGroup,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.iv.setImageResource(list.get(i).getImgSrc());
        viewHolder.tv.setText(list.get(i).getName());
        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fruit fruit=list.get(i);
                Intent intent=new Intent(context,FruitActivity.class);
                intent.putExtra("name",fruit.getName());
                intent.putExtra("img",fruit.getImgSrc());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        ImageView iv;
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv=itemView.findViewById(R.id.card_view);
            iv=itemView.findViewById(R.id.rv_item_img);
            tv=itemView.findViewById(R.id.rv_item_tv);
        }
    }
}
