package com.montecito.samayu.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.ItemBinDTO;
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
    public void onBindViewHolder(BinMonitorecyclerViewAdapter.ViewHolder holder, int position) {

        holder.itemCount.setText("bin");
        holder.tv1.setText("bin");
        holder.tv2.setText("bin");
     //   ItemAvailabilityDTO availableBinItem=binItem.get(position);


    }

    @Override
    public int getItemCount() {
        return binItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemCount,itemCount1,itemCount2,itemCount3,tv1,tv2;
        public ImageView itemImage,itemImage1,itemImage2,itemImage3;
        public ViewHolder(View itemView) {
            super(itemView);
            itemCount=(TextView)itemView.findViewById(R.id.itemCount);
            itemImage=(ImageView)itemView.findViewById(R.id.itemImage);
            tv1=(TextView)itemView.findViewById(R.id.capacity);
            tv2=(TextView)itemView.findViewById(R.id.itemName);
        }

    }

}
