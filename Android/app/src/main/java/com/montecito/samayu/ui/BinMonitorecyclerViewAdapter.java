package com.montecito.samayu.ui;

import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.service.GlobalUsage;
import com.montecito.samayu.service.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by fgs on 1/29/2018.
 */

public class BinMonitorecyclerViewAdapter extends RecyclerView.Adapter<BinMonitorecyclerViewAdapter.ViewHolder> {

    private List<ItemBinDTO> binItem;
    private Context context;
    NumberFormat numberFormat= GlobalUsage.getNumberFormat();
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
            holder.itemCount.setText(binItem.get(position).getCrateBin().getBrand()+":"+binItem.get(position).getCrateBin().getName());

            int capacity=(int)(binItem.get(position).getLastReading().getReading().getWeight() / binItem.get(position).getThresold().getMax() * 100);
            holder.itemImage.setProgress(capacity);
            holder.tv1.setText(String.valueOf(capacity)+"% ");
            holder.tv2.setText(binItem.get(position).getItem().getName());
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
        int position;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemCount=(TextView)itemView.findViewById(R.id.itemCount);
            itemImage=(ProgressBar)itemView.findViewById(R.id.capacityImage);
            tv1=(TextView)itemView.findViewById(R.id.capacity);
            tv2=(TextView)itemView.findViewById(R.id.itemName);

        }


        @Override
        public void onClick(View view) {

            SessionInfo.getInstance().setCurrentItemBinId(SessionInfo.getInstance().getItemBinDetails().get(position).get_id());
            Intent intent = new Intent(context, ItemBinDetails.class);
            context.startActivity(intent);

        }
    }
    }


