package com.example.rw16;

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

import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Fruit> list;
    private Context context;
    public MyAdapter(List<Fruit> list,Context context){
        this.list=list;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_layout,viewGroup,false);
        View view=LayoutInflater.from(context).inflate(R.layout.rv_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Fruit fruit=list.get(i);
        viewHolder.iv.setImageResource(fruit.getImgSrc());
        viewHolder.tv.setText(fruit.getName());

//        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context,FruitActivity.class);
//                intent.putExtra("name",fruit.getName());
//                intent.putExtra("img",fruit.getImgSrc());
//                context.startActivity(intent);
//            }
//        });
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
            cv=itemView.findViewById(R.id.cv);
            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.tv);
        }
    }
}
