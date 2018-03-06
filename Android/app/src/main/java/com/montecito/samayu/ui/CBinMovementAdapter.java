package com.montecito.samayu.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.montecito.samayu.dto.DeviceHistoryDTO;
import com.montecito.samayu.dto.ItemBinDetailsDTO;
import com.montecito.samayu.dto.ReplenishmentsDTO;
import com.prodcast.samayu.samayusoftcorp.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by fgs on 2/26/2018.
 */

public class CBinMovementAdapter extends BaseAdapter {
    ItemBinDetailsDTO deviceHistoryDTO;
    private static LayoutInflater inflater;
    private Context context;



    public CBinMovementAdapter(ItemBinDetails mainActivity, ItemBinDetailsDTO ItemBinDetailsDTO) {

        deviceHistoryDTO=ItemBinDetailsDTO;

        context=mainActivity;

        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return deviceHistoryDTO.getDeviceHistory().size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class Holder
    {
        //ImageView iv1;
        TextView tv1,tv2,tv3;
        int position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder;

            holder=new Holder();
            convertView = inflater.inflate(R.layout.activity_cbin_movement, null);
            holder.tv1 = (TextView) convertView.findViewById(R.id.CtriggeredOn);
            holder.tv2 = (TextView) convertView.findViewById(R.id.Cslno);
            holder.tv3 = (TextView) convertView.findViewById(R.id.Clocation);

            convertView.setTag(holder);

        holder.position=position;
        DeviceHistoryDTO deviceHistory=deviceHistoryDTO.getDeviceHistory().get(holder.position);

        SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedDate=df.format(deviceHistory.getCreated());

        String slno=deviceHistoryDTO.getCurrDevice().getSlno();
        String location=deviceHistoryDTO.getCurrDevice().getLocation();
        holder.tv1.setText(formattedDate);
        holder.tv2.setText(slno);
        holder.tv3.setText(location);
        return convertView;
    }
}
