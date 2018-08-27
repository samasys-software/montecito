package com.matriot.cbin.ui;

import android.content.Context;
import android.content.Intent;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.matriot.cbin.R;
import com.matriot.cbin.dto.ItemBinDTO;
import com.matriot.cbin.service.FormatNumber;

import com.matriot.cbin.service.SessionInfo;


import java.text.NumberFormat;
import java.util.List;

/**
 * Created by fgs on 1/29/2018.
 */

public class BinMonitorecyclerViewAdapter extends RecyclerView.Adapter<BinMonitorecyclerViewAdapter.ViewHolder> {

    private List<ItemBinDTO> binItem;
    private Context context;
    NumberFormat numberFormat= FormatNumber.getNumberFormat();
    public BinMonitorecyclerViewAdapter(Context context, List<ItemBinDTO> binItem) {
        this.binItem=binItem;
        this.context=context;
    }


    @Override
    public BinMonitorecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bin_item_cardview_data, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final BinMonitorecyclerViewAdapter.ViewHolder holder, int position) {

        if(binItem.get(position).getCrateBin()!=null) {

            if (binItem.get(position).getLastReading()!=null) {
                if (binItem.get(position).getLastReading().getReading().getWeight() == 0) {
                    holder.layout.setBackgroundResource(R.drawable.empty_background);
                    //holder.gd.setColor(Color.RED);
                    //value.lastReading.reading.status = "EMPTY";
                }	else if (binItem.get(position).getLastReading().getReading().getWeight() <= binItem.get(position).getThresold().getMin()) {
                    //value.lastReading.reading.status = "CRITICAL";
                    //holder.gd.setColor(Color.parseColor("#FFA500"));
                    holder.layout.setBackgroundResource(R.drawable.critical_background);
                }	else if (binItem.get(position).getLastReading().getReading().getWeight() >= binItem.get(position).getThresold().getNormal()) {
                    //holder.gd.setColor(Color.GREEN);
                    //value.lastReading.reading.status = "ENOUGH";
                    holder.layout.setBackgroundResource(R.drawable.enough_background);
                }	else {
                    //value.lastReading.reading.status = "RESERVE";
                    //holder.gd.setColor(Color.YELLOW);
                    holder.layout.setBackgroundResource(R.drawable.reserve_background);
                }
            }

            holder.itemCount.setText(binItem.get(position).getCrateBin().getBrand()+":"+binItem.get(position).getCrateBin().getName());

            float capacity=(binItem.get(position).getLastReading().getReading().getWeight() / binItem.get(position).getThresold().getMax() * 100);
            holder.itemImage.setProgress(Math.round(capacity));
            holder.tv1.setText(String.valueOf(numberFormat.format(capacity))+"% ");
            holder.tv2.setText(binItem.get(position).getItem().getName());



            //holder.gd.setColor(Color.RED);
            holder.position = position;
        }

      /*  ItemBinDTO availableBinItem=binItem.get(position);
        String status= availableBinItem.getStatus();
        if(status.equals("critical"))
        {
            holder.tv1.setTextColor(Color.RED);
        }
        else if(status.equals("low"))
        {

            holder.tv1.setTextColor(Color.parseColor("#ffff8800"));
        }
        else{

            holder.tv1.setTextColor(Color.parseColor("#006400"));
        }*/



    }

    @Override
    public int getItemCount() {
        return binItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView itemCount,tv1,tv2;
        public ProgressBar itemImage;
        public LinearLayout layout;
        int position;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemCount=(TextView)itemView.findViewById(R.id.itemCount);
            itemImage=(ProgressBar)itemView.findViewById(R.id.capacityImage);
            tv1=(TextView)itemView.findViewById(R.id.capacity);
            tv2=(TextView)itemView.findViewById(R.id.itemName);
            layout=(LinearLayout)itemView.findViewById(R.id.cardviewLayout);
            //layout.setBackgroundResource(R.drawable.rectangle_shape);
          //  gd = (GradientDrawable) layout.getBackground().getCurrent();*/


            //gd.setCornerRadii(new float[]{30, 30, 30, 30, 0, 0, 30, 30});
           // gd.setStroke(2, Color.parseColor("#00FFFF"), 5, 6);

        }


        @Override
        public void onClick(View view) {

            SessionInfo.getInstance().setCurrentItemBinId(SessionInfo.getInstance().getItemBinDetails().get(position).getId());
            Intent intent = new Intent(context, ItemBinDetails.class);
            context.startActivity(intent);

        }
    }



    }


