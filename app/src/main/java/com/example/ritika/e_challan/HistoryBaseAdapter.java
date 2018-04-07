package com.example.ritika.e_challan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ritika on 30-Mar-18.
 */

public class HistoryBaseAdapter extends BaseAdapter{

    Context ctx;
    ArrayList<Data> data;
    HistoryBaseAdapter(Context context,ArrayList<Data> fetchDetais){

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
        View view1 = inflater.inflate(R.layout.history_view, null);

        TextView textViewChallanNo=(TextView)view1.findViewById(R.id.textViewFetchChallanNo);
        textViewChallanNo.setText(data.get(position).challanNo);

        TextView textViewChallanName=(TextView)view1.findViewById(R.id.textViewFetchName);
        textViewChallanName.setText(data.get(position).name);

        TextView textViewChallanLicenceNo=(TextView)view1.findViewById(R.id.textViewFetchLicenceNo);
        textViewChallanLicenceNo.setText(data.get(position).licenceNo);

        TextView textViewDate=(TextView)view1.findViewById(R.id.textViewFetchDate);
        textViewDate.setText(data.get(position).date1);

        TextView textViewTime=(TextView)view1.findViewById(R.id.textViewFetchTime);
        textViewTime.setText(data.get(position).time1);

        TextView textViewChallanPlace=(TextView)view1.findViewById(R.id.textViewFetchPlace);
        textViewChallanPlace.setText(data.get(position).place);

        TextView textViewDescription=(TextView)view1.findViewById(R.id.textViewFetchDescription);
        textViewDescription.setText(data.get(position).description);

        return view1;
    }


}
