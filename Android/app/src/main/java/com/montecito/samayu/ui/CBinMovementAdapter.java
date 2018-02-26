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
import com.prodcast.samayu.samayusoftcorp.R;

import java.util.List;

/**
 * Created by fgs on 2/26/2018.
 */

public class CBinMovementAdapter extends BaseAdapter {
    ItemBinDetailsDTO deviceHistoryDTO;
    private static LayoutInflater inflater;
    private Context context;



    public CBinMovementAdapter(Context context, ItemBinDetailsDTO deviceHistoryDTO) {
        this.context = context;
        this.deviceHistoryDTO=deviceHistoryDTO;
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
        Holder holder=null;
        if (convertView == null) {
            holder=new CBinMovementAdapter.Holder();
            convertView = inflater.inflate(R.layout.cbin_movement, parent,false);
            holder.tv1 = (TextView) convertView.findViewById(R.id.RtriggeredOn);
            holder.tv2 = (TextView) convertView.findViewById(R.id.Rquantity);
            holder.tv3 = (TextView) convertView.findViewById(R.id.Rpercent);

            convertView.setTag(holder);
        }
        else{
            holder=(CBinMovementAdapter.Holder) convertView.getTag();
        }
        holder.position=position;
        DeviceHistoryDTO deviceHistory=deviceHistoryDTO.getDeviceHistory().get(holder.position);
        String TriggerdOn=String.valueOf(deviceHistory.getCreated());
        String quantity=deviceHistory.getLastDevice().getSlno();
                String percentage=deviceHistory.getLastDevice().getLocation();
        return null;
    }
}
