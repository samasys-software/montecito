package com.montecito.samayu.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.service.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;

import java.util.List;

/**
 * Created by fgs on 1/29/2018.
 */

public class BinMonitorecyclerViewAdapter extends RecyclerView.Adapter<BinMonitorecyclerViewAdapter.ViewHolder> {

    private List<ItemBinDTO> binItem;
    private Context context;
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

        holder.itemCount.setText(binItem.get(position).getCrateBin().getName());
        holder.tv1.setText(binItem.get(position).getCrateBin().getCapacity()+"%");
        holder.tv2.setText(binItem.get(position).getCurrDevice().getName());
        holder.position=position;

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
        public ImageView itemImage;
        int position;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemCount=(TextView)itemView.findViewById(R.id.itemCount);
            itemImage=(ImageView)itemView.findViewById(R.id.itemImage);
            tv1=(TextView)itemView.findViewById(R.id.capacity);
            tv2=(TextView)itemView.findViewById(R.id.itemName);

        }


        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position = " + getPosition(), Toast.LENGTH_SHORT).show();
            SessionInfo.getInstance().setCurrentItem(SessionInfo.getInstance().getItemBinDetails().get(position));
            Intent intent = new Intent(context, ItemBinDetails.class);
            context.startActivity(intent);

        }
    }
    }


