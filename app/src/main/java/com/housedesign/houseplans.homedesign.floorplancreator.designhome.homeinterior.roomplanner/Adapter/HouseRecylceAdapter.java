package com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Modal.HouseModal;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.R;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.itemActivity;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.itemViewActivity;

import java.util.ArrayList;

public class HouseRecylceAdapter extends RecyclerView.Adapter<HouseRecylceAdapter.ViewHolder> {
    Context context;
    ArrayList<HouseModal> houseModalArrayList=new ArrayList<>();

    public HouseRecylceAdapter(Context context, ArrayList<HouseModal> houseModalArrayList) {
        this.context = context;
        this.houseModalArrayList = houseModalArrayList;
    }

    @NonNull
    @Override
    public HouseRecylceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HouseRecylceAdapter.ViewHolder holder, int position) {

        HouseModal modal=houseModalArrayList.get(position);
        holder.itemImg.setBackgroundResource(modal.getImageValue());
        if(modal.getType().equals(context.getString(R.string.threed))){
            holder.itemTxt.setText(modal.getSubtype());
            if(modal.getSubtype().equals(context.getString(R.string.front))){
                holder.itemLayout.setOnClickListener(view -> {
                    Intent intent=new Intent(context, itemActivity.class);
                    intent.putExtra("case",3);
                    context.startActivity(intent);
                });
            } else if (modal.getSubtype().equals(context.getString(R.string.bedroom))) {
                holder.itemLayout.setOnClickListener(view -> {
                    Intent intent=new Intent(context, itemActivity.class);
                    intent.putExtra("case",4);
                    context.startActivity(intent);
                });
            }else if (modal.getSubtype().equals(context.getString(R.string.kitchen))) {
                holder.itemLayout.setOnClickListener(view -> {
                    Intent intent=new Intent(context, itemActivity.class);
                    intent.putExtra("case",5);
                    context.startActivity(intent);
                });
            }else if (modal.getSubtype().equals(context.getString(R.string.bathroom))) {
                holder.itemLayout.setOnClickListener(view -> {
                    Intent intent=new Intent(context, itemActivity.class);
                    intent.putExtra("case",6);
                    context.startActivity(intent);
                });
            }else if (modal.getSubtype().equals(context.getString(R.string.lounge))) {
                holder.itemLayout.setOnClickListener(view -> {
                    Intent intent=new Intent(context, itemActivity.class);
                    intent.putExtra("case",7);
                    context.startActivity(intent);
                });
            }
        }else{
            holder.itemLayout.setOnClickListener(view -> {
                Intent intent=new Intent(context,itemViewActivity.class);
                intent.putExtra("ImageValue",modal.getImageValue());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return houseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemLayout;
        ImageView itemImg;
        TextView itemTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg=itemView.findViewById(R.id.itemimg);
            itemTxt=itemView.findViewById(R.id.itemtxt);
            itemLayout=itemView.findViewById(R.id.itemlayout);
        }
    }
}
