package com.matriot.cbin.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.matriot.cbin.R;
import com.matriot.cbin.dto.ItemBinDetailsDTO;
import com.matriot.cbin.dto.ReplenishmentsDTO;
import com.matriot.cbin.service.FormatNumber;


import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 * Created by NandhiniGovindasamy on 2/26/18.
 */

public class ReplenishmentHistroyAdapter extends BaseAdapter {


        ItemBinDetailsDTO replenishmentHistroy;
        int count=0;
        Context context;
        LayoutInflater inflater;
        NumberFormat numberFormat= FormatNumber.getNumberFormat();

        public ReplenishmentHistroyAdapter(ItemBinDetails mainActivity, ItemBinDetailsDTO itemAvailabilityDTOList){

            // TODO Auto-generated constructor stub
            replenishmentHistroy=itemAvailabilityDTOList;
            //This line is commented for the purpose of server data testing
            //Collections.sort(itemAvailabilityDTOLists);

            //   System.out.println(products.size());


            context=mainActivity;
            // System.out.println(context);

            inflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return replenishmentHistroy.getReplenishments().size();
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
            TextView tv1,tv2,tv3;
            ImageView tv4;

            int position;

        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final Holder holder;
                holder=new Holder();
                convertView = inflater.inflate(R.layout.activity_replenishmenthistroy_list, null);
                //holder.tv = (TextView) convertView.findViewById(R.id.id);
                holder.tv1 = (TextView) convertView.findViewById(R.id.RtriggeredOn);
                holder.tv2 = (TextView) convertView.findViewById(R.id.Rquantity);

                // holder.iv1 = (ImageView) convertView.findViewById(R.id.statusImage);
                holder.tv3 = (TextView) convertView.findViewById(R.id.Rpercent);


                convertView.setTag(holder);


            holder.position=position;
            ReplenishmentsDTO replenishmentsDTO=replenishmentHistroy.getReplenishments().get(holder.position);

            SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String formattedDate=df.format(replenishmentsDTO.getCreated());

            holder.tv1.setText(formattedDate);

            holder.tv2.setText(String.valueOf(replenishmentsDTO.getQuantity()));
            holder.tv3.setText(numberFormat.format((replenishmentsDTO.getQuantity()/replenishmentHistroy.getThresold().getMax())*100)+"%");
            //holder.tv4.setText(status);




            return convertView;
        }



}
