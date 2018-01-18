package com.prodcast.samayusoftcorp;

import android.content.Context;
import android.view.LayoutInflater;

        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.dto.ItemAvailabilityDTO;
import com.prodcast.samayu.samayusoftcorp.R;

import java.text.NumberFormat;
        import java.util.List;

public class TaskListAdapter extends BaseAdapter {


    List<ItemAvailabilityDTO> itemAvailabilityDTOLists;
    int count=0;
    Context context;
    LayoutInflater inflater;

       public TaskListAdapter(Context Home, List<ItemAvailabilityDTO> itemAvailabilityDTOList){

        // TODO Auto-generated constructor stub
        itemAvailabilityDTOLists=itemAvailabilityDTOList;

                //   System.out.println(products.size());


        context=Home;
        // System.out.println(context);

        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return itemAvailabilityDTOLists.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        //return products.get(position);
        return  position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        //return products.indexOf(getItem(position));
        return position;
    }

    public class Holder
    {
        //ImageView iv1;
        TextView tv1,tv2,tv3,tv4;

        int position;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=null;
        if (convertView == null) {
            holder=new Holder();
            convertView = inflater.inflate(R.layout.activity_task_list_adapter, parent,false);
            //holder.tv = (TextView) convertView.findViewById(R.id.id);
            holder.tv1 = (TextView) convertView.findViewById(R.id.itemName);
            holder.tv2 = (TextView) convertView.findViewById(R.id.location);

           // holder.iv1 = (ImageView) convertView.findViewById(R.id.statusImage);
            holder.tv3 = (TextView) convertView.findViewById(R.id.availability);

            holder.tv4 = (TextView) convertView.findViewById(R.id.statusImage);
            convertView.setTag(holder);
        }
        else{
            holder=(Holder) convertView.getTag();
        }
        holder.position=position;
        ItemAvailabilityDTO availabilityDTO=itemAvailabilityDTOLists.get(holder.position);
        String itemName = availabilityDTO.getItem();
        String location = availabilityDTO.getLocation();
        String available = availabilityDTO.getAvailable();
        String status= availabilityDTO.getStatus();

        holder.tv1.setText(itemName);

        holder.tv2.setText(location);
        holder.tv3.setText(available);
        holder.tv4.setText(status);




        return convertView;
    }

}

