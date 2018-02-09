package com.montecito.samayu.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.service.GlobalUsage;
import com.prodcast.samayu.samayusoftcorp.R;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by fgs on 1/29/2018.
 */

public class BinMonitorListViewAdapter extends BaseAdapter {
    private List<ItemBinDTO> binItem;
    private static LayoutInflater inflater;
    private Context context;
    NumberFormat numberFormat= GlobalUsage.getNumberFormat();

    public BinMonitorListViewAdapter(Context context, List<ItemBinDTO> binItem) {

        this.binItem=binItem;
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return binItem.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BinMonitorListViewAdapter.Holder holder=new BinMonitorListViewAdapter.Holder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.bin_item_list_data, null);
            holder.itemCount = (TextView) convertView.findViewById(R.id.itemCount);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.itemImage);

            holder.tv1 = (TextView) convertView.findViewById(R.id.capacity);
            holder.tv2 = (TextView) convertView.findViewById(R.id.itemName);
            if(binItem.get(position).getCrateBin()!=null) {
                holder.itemCount.setText(binItem.get(position).getCrateBin().getBrand()+":"+binItem.get(position).getCrateBin().getName());
                String capacity=(numberFormat.format(binItem.get(position).getLastReading().getReading().getWeight() / binItem.get(position).getThresold().getMax() * 100))+"%";
                holder.tv1.setText(capacity);
                holder.tv2.setText(binItem.get(position).getItem().getName());

            }


            /*ItemBinDTO availableBinItem=binItem.get(position);
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
        return convertView;
    }

    public class Holder {
        public TextView itemCount,tv1,tv2;
        public ImageView itemImage;
    }
}
