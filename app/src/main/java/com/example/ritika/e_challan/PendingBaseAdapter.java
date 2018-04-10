package com.example.ritika.e_challan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ritika on 04-Apr-18.
 */

public class PendingBaseAdapter extends BaseAdapter{

    Context ctx;
    ArrayList<Data> data;
    PendingBaseAdapter(Context context,ArrayList<Data> fetchDetais){

        ctx=context;
        data=fetchDetais;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.pending_view, null);

        TextView textViewChallanNo=(TextView)view1.findViewById(R.id.textViewPendingFetchChallanNo);
        textViewChallanNo.setText(data.get(position).challanNo);


        TextView textViewDate=(TextView)view1.findViewById(R.id.textViewPendingFetchDate);
        textViewDate.setText(data.get(position).date1);

        TextView textViewChallanFineAmount=(TextView)view1.findViewById(R.id.textViewPeningFetchFineAmount);
        textViewChallanFineAmount.setText(data.get(position).fineAmount);

        return view1;
    }


}
