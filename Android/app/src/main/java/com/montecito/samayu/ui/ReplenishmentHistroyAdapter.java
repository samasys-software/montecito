package com.montecito.samayu.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.ItemBinDetailsDTO;
import com.montecito.samayu.dto.ItemDTO;
import com.montecito.samayu.dto.ReplenishmentsDTO;
import com.prodcast.samayu.samayusoftcorp.R;

import java.util.List;

/**
 * Created by NandhiniGovindasamy on 2/26/18.
 */

public class ReplenishmentHistroyAdapter extends BaseAdapter {


        ItemBinDetailsDTO replenishmentHistroy;
        int count=0;
        Context context;
        LayoutInflater inflater;

        public ReplenishmentHistroyAdapter(Context Home, ItemBinDetailsDTO itemAvailabilityDTOList){

            // TODO Auto-generated constructor stub
            replenishmentHistroy=itemAvailabilityDTOList;
            //This line is commented for the purpose of server data testing
            //Collections.sort(itemAvailabilityDTOLists);

            //   System.out.println(products.size());


            context=Home;
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
            com.montecito.samayu.ui.ReplenishmentHistroyAdapter.Holder holder=null;
            if (convertView == null) {
                holder=new com.montecito.samayu.ui.ReplenishmentHistroyAdapter.Holder();
                convertView = inflater.inflate(R.layout.activity_replenishmenthistroy_list, parent,false);
                //holder.tv = (TextView) convertView.findViewById(R.id.id);
                holder.tv1 = (TextView) convertView.findViewById(R.id.RtriggeredOn);
                holder.tv2 = (TextView) convertView.findViewById(R.id.Rquantity);

                // holder.iv1 = (ImageView) convertView.findViewById(R.id.statusImage);
                holder.tv3 = (TextView) convertView.findViewById(R.id.Rpercent);


                convertView.setTag(holder);
            }
            else{
                holder=(com.montecito.samayu.ui.ReplenishmentHistroyAdapter.Holder) convertView.getTag();
            }
            holder.position=position;
            ReplenishmentsDTO replenishmentsDTO=replenishmentHistroy.getReplenishments().get(holder.position);


            holder.tv1.setText(String.valueOf(replenishmentsDTO.getCreated ()));

            holder.tv2.setText(String.valueOf(replenishmentsDTO.getQuantity()));
            holder.tv3.setText(((replenishmentsDTO.getQuantity()/replenishmentHistroy.getThresold().getMax())*100)+"%");
            //holder.tv4.setText(status);




            return convertView;
        }



}
